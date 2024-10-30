package CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities;


import CarmineGargiulo.FS0624_Unit5_Week2_Day3.enums.TipoBlog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogs")
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "title", nullable = false)
    private String titolo;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private TipoBlog categoria;
    private String cover;
    @Column(name = "content", nullable = false)
    private String contenuto;
    @Column(name = "read_time")
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Autore autore;

    public Blog(String titolo, String contenuto, int tempoDiLettura, Autore autore) {
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autore = autore;
    }
}
