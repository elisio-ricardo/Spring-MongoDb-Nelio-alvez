package com.MongoDB.workshop.config;

import com.MongoDB.workshop.DTO.AuthorDTO;
import com.MongoDB.workshop.DTO.ComentDTO;
import com.MongoDB.workshop.domain.Post;
import com.MongoDB.workshop.domain.User;
import com.MongoDB.workshop.repository.PostRepository;
import com.MongoDB.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();//deleta todo o banco
        postRepository.deleteAll();

        User maria = new User(null, "Maria", "maria@gmail.com");
        User alex = new User(null, "Alex", "alex@gmail.com");
        User pedro = new User(null, "Pedro", "pedro@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, pedro));

        Post post1 = new Post(null, sdf.parse("21/01/2018"), "Partiu viagem", "Vou viajar para são Paulo", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei Feliz Hoje", new AuthorDTO(maria));

        ComentDTO c1 = new ComentDTO("Boa viagem", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        ComentDTO c2 = new ComentDTO("Tudo bem !", sdf.parse("10/12/2020"), new AuthorDTO(pedro));
        ComentDTO c3 = new ComentDTO("Ate amanha", sdf.parse("21/06/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
