package com.andreas.dummyProjektboerse.Controller;

import com.andreas.dummyProjektboerse.Entity.Posts;
import com.andreas.dummyProjektboerse.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HtmlController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String postsForm(Model model) {
        model.addAttribute("addPost", new Posts());
        model.addAttribute("posts", postRepository.findAll());
        return "posts";
    }

    @PostMapping("/")
    public String postsSubmit(@ModelAttribute("addPost") Posts posts) {
        posts.setStatus("publish");
        postRepository.save(posts);
        return "redirect:";
    }
}
