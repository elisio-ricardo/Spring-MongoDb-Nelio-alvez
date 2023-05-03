package com.MongoDB.workshop.services;

import com.MongoDB.workshop.DTO.UserDTO;
import com.MongoDB.workshop.domain.User;
import com.MongoDB.workshop.repository.UserRepository;
import com.MongoDB.workshop.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        findById(id);//para garantir que o obj existe, se não existir joga uma exception
        userRepository.deleteById(id);
    }

    //este metodo poder ser inserido na classe userDto, mas deixou aqui para usar em outros metodos
    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
