package com.MongoDB.workshop.repository;

import com.MongoDB.workshop.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    //AS DUAS QUERYS FAZEM A MESMA COISA MAS DE MANEIRAS DIFERENTES


    //Usando uma query methods
    // List<Post> findByTitleContaining(String text);

    //Dessa forma ignora maiscula e minuscula
    List<Post> findByTitleContainingIgnoreCase(String text);

    // ?0 significa que é o primeiro parametro
    // 'i' significa para ignorar maiusculas e minusculas
    @Query("{'title': {$regex: ?0, $options: 'i'}}")
    List<Post> searchTitle(String text);

    //coments.text busca um texto dentro de uma lista
    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'coments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
