package com.example.contasapagar.contasapagar.services;

import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository  userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public void createUser(UserEntity user){
        userRepository.save(user);
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userRepository.findByemail(email);
    }

    public List<UserEntity> listarUsuarios(){
        return userRepository.findAll();
    }

}
