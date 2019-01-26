package com.andreas.dummyProjektboerse.Controller;

import com.andreas.dummyProjektboerse.Entity.PostToWp;
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
        model.addAttribute("sendToWp", new PostToWp(1L,"send"));
        return "posts";
    }

    @PostMapping("/")
    public String postsSubmit(@ModelAttribute("addPost") Posts posts, @ModelAttribute("sendToWp") PostToWp sendToWp) {
        if(sendToWp.getName() != null) {
            System.out.println("Send this data to WordPress: YES");
        }
        else {
            System.out.println("Send this data to WordPress: NO");
        }

        posts.setStatus("publish");
        postRepository.save(posts);
        return "redirect:";
    }
}
