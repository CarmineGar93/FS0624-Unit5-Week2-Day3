package CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Il record con id " + id + " non è stato trovato");
    }
}
