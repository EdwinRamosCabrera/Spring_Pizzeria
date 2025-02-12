package com.platzi.pizzeria_presto.servicio;

import com.platzi.pizzeria_presto.persistencia.entidad.User;
import com.platzi.pizzeria_presto.persistencia.entidad.UserRole;
import com.platzi.pizzeria_presto.persistencia.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserSecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(int userId){
        return this.userRepository.findById(userId);
    }

    public List<User> getUserAll(){
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User " + username + " not found"));
        System.out.println(user); // imprime los datos del user, pero primero se tiene que crear el método toString en la clase User

        String[] roles = user.getUserRoleList().stream().map(UserRole::getRolName).toArray(String[]::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .accountLocked(user.getLocked())
                .disabled(user.getDisable())
                .build();
    }
}
