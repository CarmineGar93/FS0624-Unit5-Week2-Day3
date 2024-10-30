package CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Autore {
    private static int count = 1;
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autore(String nome, String cognome, String email) {
        this.id = count;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        count++;
    }
}
