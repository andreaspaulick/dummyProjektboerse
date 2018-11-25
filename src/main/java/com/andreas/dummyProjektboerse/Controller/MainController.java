package com.andreas.dummyProjektboerse.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.andreas.dummyProjektboerse.Repository.PostRepository;
import com.andreas.dummyProjektboerse.Entity.Posts;

import java.util.Map;


@RestController
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/posts/add") // Map ONLY GET Requests
    public @ResponseBody
    String addPost (@RequestParam String title
            , @RequestParam String content, @RequestParam String status) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Posts n = new Posts();
        n.setTitle(title);
        n.setContent(content);
        n.setStatus(status);
        postRepository.save(n);
        return "Saved";
    }

    @PostMapping(path="/posts/jsonadd") // Map ONLY GET Requests
    public Posts addJsonPost (@RequestBody Map<String,String> body) {

        Posts n = new Posts();
        n.setTitle(body.get("title"));
        n.setContent(body.get("content"));
        n.setStatus(body.get("status"));
        return postRepository.save(n);
    }

    @GetMapping(path="/posts/delete") // Map ONLY GET Requests
    public @ResponseBody
    String deletePost (@RequestParam int id) {

        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return "Post " + id + " deleted";
        }
        else
            return "ERROR: ID " + id + " not found in database";
    }

    @GetMapping(path="/posts")
    public @ResponseBody Iterable<Posts> getAllPosts() {
        // This returns a JSON or XML with the users
        return postRepository.findAll();
    }

}
