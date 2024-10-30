package CarmineGargiulo.FS0624_Unit5_Week2_Day3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BlogPayload {
    private String titolo;
    private String categoria;
    private String contenuto;
    private String tempoDiLettura;
    private UUID autoreId;
}
