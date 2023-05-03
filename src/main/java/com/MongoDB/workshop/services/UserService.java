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

    public User update(User obj) {
        Optional<User> newObj = userRepository.findById(obj.getId());
        User objUser = fromOptionalToUser(newObj);
        updateData(objUser, obj);
        userRepository.save(objUser);
        return objUser;
    }

    private User fromOptionalToUser(Optional<User> newObj) {
        User userObj = new User();
        userObj.setId(newObj.get().getId());
        userObj.setName(newObj.get().getName());
        userObj.setEmail(newObj.get().getEmail());
        return userObj;
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
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
