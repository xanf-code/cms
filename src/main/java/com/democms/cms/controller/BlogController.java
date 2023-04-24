package com.democms.cms.controller;

import com.democms.cms.entity.Blog;
import com.democms.cms.entity.User;
import com.democms.cms.repository.BlogRepository;
import com.democms.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create/blog")
    Blog createBlog(@RequestBody Blog blogRequest) {
        return blogRepository.save(blogRequest);
    }

    @GetMapping("/v2/blogs")
    ResponseEntity<Map<String,?>> getBlogs(@RequestParam("id") Long user_id){
       Optional<User> user = userRepository.findById(user_id);
        if(user.isPresent()){
            List<Blog> blogs = user.get().getBlogs();
            blogs.sort(Comparator.comparing(Blog::getBlog_id, Comparator.reverseOrder()));
            Map<String, Object> response = new HashMap<>();
            response.put("blogs",blogs);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message","No Blogs found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
