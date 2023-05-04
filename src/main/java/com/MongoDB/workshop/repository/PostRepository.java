package com.MongoDB.workshop.repository;

import com.MongoDB.workshop.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //Usando uma query methods
    // List<Post> findByTitleContaining(String text);

    //Dessa forma ignora maiscula e minuscula
    List<Post> findByTitleContainingIgnoreCase(String text);

    // ?0 significa que é o primeiro parametro
    // 'i' significa para ignorar maiusculas e minusculas
    @Query("{'title': {$regex: ?0, $options: 'i'}}")
    List<Post> searchTitle(String text);
}
