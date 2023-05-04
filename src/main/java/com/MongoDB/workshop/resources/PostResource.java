package com.MongoDB.workshop.resources;


import com.MongoDB.workshop.domain.Post;
import com.MongoDB.workshop.resources.util.URL;
import com.MongoDB.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate) {
        text = URL.decodeParam(text);

        //o segundo parametro trás a data minima do java, caso não venha uma data
        Date min = URL.convertDate(minDate, new Date(0L));

        //o segundo parametro trás a data do instante da chamada, caso não venha uma data
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }

}
