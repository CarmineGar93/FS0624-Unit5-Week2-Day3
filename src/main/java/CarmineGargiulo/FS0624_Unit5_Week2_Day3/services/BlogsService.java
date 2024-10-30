package CarmineGargiulo.FS0624_Unit5_Week2_Day3.services;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Blog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.enums.TipoBlog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.NotFoundException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.BlogPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogsService {
    private List<Blog> blogList = new ArrayList<>();

    public List<Blog> findAll() {
        return this.blogList;
    }

    public Blog saveBlog(BlogPayload body) {
        Blog blog = new Blog(body.getTitolo(), body.getContenuto(), Integer.parseInt(body.getTempoDiLettura()));
        List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
        List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
        if (!tipiString.contains(body.getCategoria().toUpperCase())) blog.setCategoria(TipoBlog.GENERICO);
        else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
        blog.setCover("https://placedog.net/200/200");
        this.blogList.add(blog);
        return blog;
    }

    public Blog findBlogById(int blogId) {
        if (blogList.stream().noneMatch(blog -> blogId == blog.getId())) throw new NotFoundException(blogId);
        return blogList.stream().filter(blog -> blogId == blog.getId()).toList().getFirst();
    }

    public Blog findBlogByIdAndUpdate(int blogId, BlogPayload body) {
        Blog blog = findBlogById(blogId);
        blog.setTitolo(body.getTitolo());
        blog.setContenuto(body.getContenuto());
        blog.setTempoDiLettura(Integer.parseInt(body.getTempoDiLettura()));
        List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
        List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
        if (!tipiString.contains(body.getCategoria().toUpperCase())) blog.setCategoria(TipoBlog.GENERICO);
        else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
        return blog;
    }

    public void findBlogByIdAndDelete(int blogId) {
        Blog blog = findBlogById(blogId);
        blogList.remove(blog);
    }
}
