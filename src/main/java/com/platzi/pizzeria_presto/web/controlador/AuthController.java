package com.platzi.pizzeria_presto.web.controlador;

import com.platzi.pizzeria_presto.service.dto.LoginDTO;
import com.platzi.pizzeria_presto.web.configuracion.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());  // creamos un UsernamePasswordAuthenticationToken con los datos que nos llegó en el request body de login
        Authentication authentication = this.authenticationManager.authenticate(login); // creamos un objeto de autenticación que como valor tiene el llamado al authenticate del authenticationManager (internamente este va ala authenticated Provider y este va al userDetailService que en este caso será el nuestro que es el userSecurityService).
        // El userDetailService recupera a su usuario y cuando lo tiene en su poder el authentication Provider verifica la contraseña del usuario en la BD contra la contraseña que recibimos en el login DTO, si no es correcto el flujo se cancela, y se lanza una respuesta de que el usuario no se puede autenticar
        System.out.println(authentication.isAuthenticated()); // true si el usuario se logro autenticar
        System.out.println(authentication.getPrincipal()); // imprime el usuario como tal que está dentro del contexto de seguridad de Spring

        String jwt = this.jwtUtil.create(loginDTO.getUsername()); // con eso creamos un JWT
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build(); // nos responderá con un estatus 200 y un encabezado de authorization y con el rá el JWT
    }
}
