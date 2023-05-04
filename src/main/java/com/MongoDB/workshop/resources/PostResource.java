package com.MongoDB.workshop.resources;


import com.MongoDB.workshop.domain.Post;
import com.MongoDB.workshop.resources.util.URL;
import com.MongoDB.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch")//é request param quando o valor vem /titlesearch?text=xxxxxx
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
       text = URL.decodeParam(text);
        List<Post> listaPost = postService.findByTitle(text);
        return ResponseEntity.ok().body(listaPost);
    }

}
