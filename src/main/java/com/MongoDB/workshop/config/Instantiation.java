package com.MongoDB.workshop.config;

import com.MongoDB.workshop.domain.User;
import com.MongoDB.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();//deleta todo o banco

        User maria = new User(null, "Maria", "maria@gmail.com");
        User alex = new User(null, "Alex", "alex@gmail.com");
        User pedro = new User(null, "Pedro", "pedro@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, pedro));
    }
}
