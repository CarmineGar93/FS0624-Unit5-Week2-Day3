package CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities;


import CarmineGargiulo.FS0624_Unit5_Week2_Day3.enums.TipoBlog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Blog {
    private static int count = 1;
    private int id;
    private String titolo;
    private TipoBlog categoria;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    public Blog(String titolo, String contenuto, int tempoDiLettura) {
        this.id = count;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        count++;
    }
}
