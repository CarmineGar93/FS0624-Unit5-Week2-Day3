package CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
@NoArgsConstructor
public class Autore {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String nome;
    @Column(name = "surname", nullable = false)
    private String cognome;
    @Column(nullable = false)
    private String email;
    @Column(name = "birthday", nullable = false)
    private LocalDate dataDiNascita;
    private String avatar;
    @OneToMany(mappedBy = "autore")
    @JsonIgnore
    private List<Blog> blogList;

    public Autore(String nome, String cognome, String email, String avatar) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = avatar;
    }
}
