package CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class AutorePayload {
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
}
