package com.andreas.dummyProjektboerse.Controller;

import com.andreas.dummyProjektboerse.Entity.TitleOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.andreas.dummyProjektboerse.Repository.PostRepository;
import com.andreas.dummyProjektboerse.Entity.Posts;

import java.util.*;


@RestController
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/posts/add")
    public @ResponseBody
    String addPost (@RequestParam String title
            , @RequestParam String content, @RequestParam String status) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
//
        Posts n = new Posts();
        n.setTitle(title);
        n.setContent(content);
        n.setStatus(status);
        postRepository.save(n);
        return "Saved <a href=\"http://localhost:8045/posts\"><br><br>Back to Posts</p>";
    }


    @PostMapping(path="/posts/jsonadd")
    public ResponseEntity addJsonPost (@RequestBody Map<String,String> body) {

        Posts n = new Posts();
        n.setTitle(body.get("title"));
        n.setContent(body.get("content"));
        n.setStatus(body.get("status"));
        n.setCourse(body.get("course"));
        n.setStart(body.get("start"));
        n.setEnd(body.get("end"));
        n.setMax_party(body.get("max_party"));
        n.setTags(body.get("tags"));
        n.setUser_login(body.get("user_login"));

        if(n.getContent()==null || n.getStatus()==null|| n.getTitle()==null)
            return new ResponseEntity<>("Error: Missing or invalid JSON Key(s).", null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(postRepository.save(n),HttpStatus.OK);
    }

    @GetMapping(path="/posts/delete")
    public @ResponseBody
    String deletePost (@RequestParam int id) {

        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return "Post " + id + " deleted <a href=\"http://localhost:8045/posts\"><br><br>Back to Posts</p>";
        }
        else
            throw new ResourceNotFoundException();
    }

    @RequestMapping(path="/posts/deleteall", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAllPosts() {
        // Clears the database
        postRepository.deleteAll();
        return "Database erased";
    }

    @GetMapping(path="/posts")
    public @ResponseBody List<Posts> getAllPosts() {
        // This returns a JSON or XML with the posts
        return postRepository.findAll();
    }

    @GetMapping(path="/posts/titles")
    public @ResponseBody List<TitleOnly> getOnlyTitles() {
        // This returns a JSON or XML with the titles only
        List<Posts> before = postRepository.findAll();
        List<TitleOnly> after = new ArrayList<>();

        for(Posts element : before){
            after.add(new TitleOnly(element.getTitle()));
        }
        return after;
    }

    @GetMapping(path = "/posts/id/{id}")
    public @ResponseBody Optional getByID(@PathVariable int id) {
        if(postRepository.existsById(id))
            return postRepository.findById(id);
        else
            throw new ResourceNotFoundException();
    }
}
