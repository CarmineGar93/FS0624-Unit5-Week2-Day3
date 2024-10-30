package CarmineGargiulo.FS0624_Unit5_Week2_Day3.services;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.NotFoundException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.AutorePayload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AutoriService {
    private List<Autore> autoriList = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('zzzz')'",
            Locale.ENGLISH);

    public List<Autore> findAll() {
        return autoriList;
    }

    public Autore saveAutore(AutorePayload body) {
        Autore autore = new Autore(body.getNome(), body.getCognome(), body.getEmail());
        autore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());
        autore.setDataDiNascita(LocalDate.parse(body.getDataDiNascita(), formatter));
        autoriList.add(autore);
        return autore;

    }

    public Autore findAutoreById(int autoreId) {
        if (autoriList.stream().noneMatch(autore -> autoreId == autore.getId())) throw new NotFoundException(autoreId);
        return autoriList.stream().filter(autore -> autoreId == autore.getId()).toList().getFirst();
    }

    public Autore findAutoreByIdAndUpdate(int autoreId, AutorePayload body) {
        Autore autore = findAutoreById(autoreId);
        autore.setNome(body.getNome());
        autore.setCognome(body.getCognome());
        autore.setEmail(body.getEmail());
        autore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());
        autore.setDataDiNascita(LocalDate.parse(body.getDataDiNascita(), formatter));
        return autore;
    }

    public void findAutoreByIdAndDelete(int autoreId) {
        Autore autore = findAutoreById(autoreId);
        autoriList.remove(autore);
    }
}
