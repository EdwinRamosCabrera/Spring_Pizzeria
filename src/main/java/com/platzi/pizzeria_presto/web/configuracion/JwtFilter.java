package com.platzi.pizzeria_presto.web.configuracion;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService; // No tenemos que indicarle que es explícitamente el UserSecurityService ya que este implementa el UserDetailService,
                                                          // ya gracias al polimorfismo y los beans de Spring podemos utilizar la interfaz en lugar de sus implementaciones
    // el sabra que cuando estemos usando el UserDetailService nos estamos refiriendo al UserSecurityService porque es el único dentro de nuestro proyecto que implementa esa interfaz
    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService){
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Validar que el Header Authorization sea válido
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // obtenemos el encabezado de la petición del parámetro request, de su método getHeaders y HttpHeaders

            if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){  // verificamos si nos sirve o no
                filterChain.doFilter(request, response); // como no nos sirve le decimos al filterChain que continue su trabajo y le enviamos el request y response, esto se hace porque sino viene
                // ningún Header de seguridad o autorización entonces no nos interesa debido a que es una petición que no se va a resolver en términos de seguridad y no vamos a cargar nada en el contexto de seguridad de nuestra app
                return; // es importante que cuando le decimos al filtro de seguridad que continue porque no será una autenticación correcta (respuesta 401 o 403), le demos return, a pesar de que este método sea void con este return estamos garantizando de que no siga con lo que se encuentre abajo
            }           // con el fin de cancelar ya la ejecución del método (tendremos una respuesta 401 o 403) y que él vuelva al filterChain y siga validando las otras cosas.

        // 2. Validar que el JWT sea válido
            String jwt = authHeader.split(" ")[1].trim(); // o también String jwt = authHeader.replace("Bearer ", "")

            if(!this.jwtUtil.isValid(jwt)){
                filterChain.doFilter(request, response);
                return;
            }

        // 3. Cargar el usuario desde el UserDetailsService
            String username = this.jwtUtil.getUsername(jwt);     // obtenemos el username de nuestro JWT
            User user = (User) this.userDetailsService.loadUserByUsername(username); // usamos el User de import org.springframework.security.core.userdetails.User;
        
        // 4. Cargar al usuario en el contexto de seguridad
             // para que la petición se resuelva bien primero teníamos que cargar el usuario de nuestro repositorio o de nuestro UserDetailService y cargarlo dentro del contexto de seguridad de Spring
             // para que le diga a los otros filtros que este filtro resolvió la petición de manera correcta en términos de seguridad.
             // El UsernamePasswordAuthenticationToken es el mismo que usamos en el AuthController para el login, pero esta vez no vamos a autenticar con usuario y contraseña xq no los estamos recibiendo sino que estamos recibiendo el JWT que generamos al momento de iniciar sesión
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(  // Elegimos el que tiene 3 parámetros (usuario, contraseña y autoridades) y devuelve un true xq el que solo recibe 2 devuelve false
                user.getUsername(), user.getPassword(), user.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // cargamos esta autenticación al contexto de seguridad de Spring y está dentro de la clase estática SecurityContextHolder
            System.out.println(authenticationToken);
            filterChain.doFilter(request, response); // los anteriores doFilter no se cargaron en el contexto de seguridad porque no fueron válidos, en cambio aca si se cargan al contexto de seguridad antes de decirle al filterChain que siga con sus filtros
    }
}
