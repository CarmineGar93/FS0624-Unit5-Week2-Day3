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
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);
    }

    public Blog saveBlog(BlogPayload body) {
        if (body.getTitolo() == null || body.getAutoreId() == null || body.getContenuto() == null)
            throw new BadRequestException("Formato JSON non supportato");
        try {
            Autore autore = autoriService.findAutoreById(body.getAutoreId());
            Blog blog = new Blog(body.getTitolo(), body.getContenuto(), autore);
            blog.setCover("https://placedog.net/200/200");
            if (body.getTempoDiLettura() != null) blog.setTempoDiLettura(Integer.parseInt(body.getTempoDiLettura()));
            if (body.getCategoria() != null) {
                List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
                List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
                if (!tipiString.contains(body.getCategoria().toUpperCase()))
                    throw new BadRequestException("Categoria non supportata");
                else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
            }
            return blogsRepository.save(blog);
        } catch (NotFoundException e) {
            throw new BadRequestException("Non esiste nessun utente con id " + body.getAutoreId());
        } catch (NumberFormatException e) {
            throw new BadRequestException("Tempo di lettura deve essere un numero intero");
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
        if (body.getTempoDiLettura() != null) {
            try {
                blog.setTempoDiLettura(Integer.parseInt(body.getTempoDiLettura()));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Tempo di lettura deve essere un numero intero");
            }
        }
        if (body.getCategoria() != null) {
            List<TipoBlog> tipi = Arrays.stream(TipoBlog.values()).toList();
            List<String> tipiString = List.of("PERSONALE", "MULTIMEDIALE", "GENERICO", "AZIENDALE", "NOTIZIE");
            if (!tipiString.contains(body.getCategoria().toUpperCase()))
                throw new BadRequestException("Categoria non supportata");
            else blog.setCategoria(tipi.get(tipiString.indexOf(body.getCategoria().toUpperCase())));
        }
        return blogsRepository.save(blog);
    }

    public void findBlogByIdAndDelete(UUID blogId) {
        Blog blog = findBlogById(blogId);
        blogsRepository.delete(blog);
    }
}
