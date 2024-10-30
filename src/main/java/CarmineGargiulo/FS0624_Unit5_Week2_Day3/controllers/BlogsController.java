package CarmineGargiulo.FS0624_Unit5_Week2_Day3.controllers;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Blog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.BlogPayload;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    @GetMapping
    public List<Blog> getBlogs() {
        return blogsService.findAll();
    }

    @GetMapping("/{blogId}")
    public Blog getBlog(@PathVariable UUID blogId) {
        return blogsService.findBlogById(blogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog postBlog(@RequestBody BlogPayload body) {
        return blogsService.saveBlog(body);
    }

    @PutMapping("/{blogId}")
    public Blog putBlog(@RequestBody BlogPayload body, @PathVariable UUID blogId) {
        return blogsService.findBlogByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable UUID blogId) {
        blogsService.findBlogByIdAndDelete(blogId);
    }


}
