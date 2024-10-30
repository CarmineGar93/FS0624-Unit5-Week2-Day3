package CarmineGargiulo.FS0624_Unit5_Week2_Day3.services;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Blog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.enums.TipoBlog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.NotFoundException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.BlogPayload;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BlogsService {
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AutoriService autoriService;

    public List<Blog> findAll() {
        return blogsRepository.findAll();
    }

    public Blog saveBlog(BlogPayload body) {
        Autore autore = autoriService.findAutoreById(body.getAutoreId());
        Blog blog = new Blog(body.getTitolo(), body.getContenuto(), Integer.parseInt(body.getTempoDiLettura()), autore);
        List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
        List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
        if (!tipiString.contains(body.getCategoria().toUpperCase())) blog.setCategoria(TipoBlog.GENERICO);
        else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
        blog.setCover("https://placedog.net/200/200");
        return blogsRepository.save(blog);

    }

    public Blog findBlogById(UUID blogId) {
        return blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Il blog", blogId));
    }

    public Blog findBlogByIdAndUpdate(UUID blogId, BlogPayload body) {
        Blog blog = findBlogById(blogId);
        Autore autore = autoriService.findAutoreById(body.getAutoreId());
        blog.setTitolo(body.getTitolo());
        blog.setContenuto(body.getContenuto());
        blog.setTempoDiLettura(Integer.parseInt(body.getTempoDiLettura()));
        blog.setAutore(autore);
        List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
        List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
        if (!tipiString.contains(body.getCategoria().toUpperCase())) blog.setCategoria(TipoBlog.GENERICO);
        else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
        return blogsRepository.save(blog);
    }

    public void findBlogByIdAndDelete(UUID blogId) {
        Blog blog = findBlogById(blogId);
        blogsRepository.delete(blog);
    }
}
