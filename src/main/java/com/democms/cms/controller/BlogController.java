package com.democms.cms.controller;

import com.democms.cms.entity.Blog;
import com.democms.cms.entity.User;
import com.democms.cms.repository.BlogRepository;
import com.democms.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create/blog")
    public Blog createBlog(@RequestBody Blog blogRequest) {
        return blogRepository.save(blogRequest);
    }
}
