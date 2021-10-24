package com.wsbjava.todolsit.service;

import com.wsbjava.todolsit.model.User;
import com.wsbjava.todolsit.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s)  {
        User user = userRepository.findUserByUsername(s)
                .orElseThrow( ()-> new UsernameNotFoundException("user with username "+s+" not found"));
        return user;
    }


}
