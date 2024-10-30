package CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String risorsa, UUID id) {
        super(risorsa + " con id " + id + " non è stato trovato");
    }
}
