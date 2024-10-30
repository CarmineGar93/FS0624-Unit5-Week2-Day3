package CarmineGargiulo.FS0624_Unit5_Week2_Day3.controllers;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads.AutorePayload;
import CarmineGargiulo.FS0624_Unit5_Week2_Day3.services.AutoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autori")
public class AutoriController {
    @Autowired
    private AutoriService autoriService;

    @GetMapping
    public List<Autore> getAutori() {
        return autoriService.findAll();
    }

    @GetMapping("/{autoreId}")
    public Autore getAutore(@PathVariable UUID autoreId) {
        return autoriService.findAutoreById(autoreId);
    }

    @PostMapping
    public Autore postAutore(@RequestBody AutorePayload body) {
        return autoriService.saveAutore(body);
    }

    @PutMapping("/{autoreId}")
    public Autore modifyAutore(@PathVariable UUID autoreId, @RequestBody AutorePayload body) {
        return autoriService.findAutoreByIdAndUpdate(autoreId, body);
    }

    @DeleteMapping("/{autoreId}")
    public void deleteAutore(@PathVariable UUID autoreId) {
        autoriService.findAutoreByIdAndDelete(autoreId);
    }
}
