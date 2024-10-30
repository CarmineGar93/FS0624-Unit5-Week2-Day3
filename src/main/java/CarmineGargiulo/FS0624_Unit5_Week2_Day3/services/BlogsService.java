package CarmineGargiulo.FS0624_Unit5_Week2_Day3.services;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Blog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.enums.TipoBlog;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.NotFoundException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.BlogPayload;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Blog> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);
    }

    public Blog saveBlog(BlogPayload body) {
        try {
            Autore autore = autoriService.findAutoreById(body.getAutoreId());
            Blog blog = new Blog(body.getTitolo(), body.getContenuto(), Integer.parseInt(body.getTempoDiLettura()),
                    autore);
            List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
            List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
            if (!tipiString.contains(body.getCategoria().toUpperCase())) blog.setCategoria(TipoBlog.GENERICO);
            else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
            blog.setCover("https://placedog.net/200/200");
            return blogsRepository.save(blog);
        } catch (NotFoundException e) {
            throw new BadRequestException("Non esiste nessun utente con id " + body.getAutoreId());
        }
    }

    public Blog findBlogById(UUID blogId) {
        return blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Il blog", blogId));
    }

    public Blog findBlogByIdAndUpdate(UUID blogId, BlogPayload body) {
        Blog blog = findBlogById(blogId);
        try {
            Autore autore = autoriService.findAutoreById(body.getAutoreId());
            blog.setAutore(autore);
        } catch (NotFoundException e) {
            throw new BadRequestException("Non esiste nessun utente con id " + body.getAutoreId());
        }
        blog.setTitolo(body.getTitolo());
        blog.setContenuto(body.getContenuto());
        blog.setTempoDiLettura(Integer.parseInt(body.getTempoDiLettura()));
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
