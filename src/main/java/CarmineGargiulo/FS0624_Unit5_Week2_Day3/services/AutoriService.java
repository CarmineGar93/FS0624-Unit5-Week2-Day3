package CarmineGargiulo.FS0624_Unit5_Week2_Day3.services;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions.NotFoundException;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.AutorePayload;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.repositories.AutoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Service
public class AutoriService {
    @Autowired
    private AutoriRepository autoriRepository;

    @Autowired
    private List<DateTimeFormatter> formatters;

    public List<Autore> findAll() {
        return autoriRepository.findAll();
    }

    public Autore saveAutore(AutorePayload body) {
        if (body.getDataDiNascita() == null || body.getCognome() == null || body.getEmail() == null || body.getNome() == null)
            throw new BadRequestException("Formato JSON non supportato");
        if (autoriRepository.existsByEmail(body.getEmail()))
            throw new BadRequestException("Email " + body.getEmail() + " già in uso");
        Autore autore = new Autore(body.getNome(), body.getCognome(), body.getEmail(), "https://ui-avatars" +
                ".com/api/?name=" + body.getNome() + "+" + body.getCognome());
        boolean converted = false;
        for (DateTimeFormatter formatter : formatters) {
            try {
                autore.setDataDiNascita(LocalDate.parse(body.getDataDiNascita(), formatter));
                converted = true;
            } catch (DateTimeParseException ignored) {

            }
        }
        if (!converted) throw new BadRequestException("Formato data non supportato");
        return autoriRepository.save(autore);

    }

    public Autore findAutoreById(UUID autoreId) {
        return autoriRepository.findById(autoreId).orElseThrow(() -> new NotFoundException("L'autore", autoreId));
    }

    public Autore findAutoreByIdAndUpdate(UUID autoreId, AutorePayload body) {
        if (body.getDataDiNascita() == null || body.getCognome() == null || body.getEmail() == null || body.getNome() == null)
            throw new BadRequestException("Formato JSON errato");
        Autore autore = findAutoreById(autoreId);
        if (!autore.getEmail().equals(body.getEmail())) {
            if (autoriRepository.existsByEmail(body.getEmail()))
                throw new BadRequestException("Email " + body.getEmail() + " già in uso");
            else autore.setEmail(body.getEmail());
        }
        autore.setNome(body.getNome());
        autore.setCognome(body.getCognome());
        autore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());
        boolean converted = false;
        for (DateTimeFormatter formatter : formatters) {
            try {
                autore.setDataDiNascita(LocalDate.parse(body.getDataDiNascita(), formatter));
                converted = true;
            } catch (DateTimeParseException ignored) {

            }
        }
        if (!converted) throw new BadRequestException("Formato data non supportato");
        return autoriRepository.save(autore);
    }

    public void findAutoreByIdAndDelete(UUID autoreId) {
        Autore autore = findAutoreById(autoreId);
        autoriRepository.delete(autore);
    }
}
