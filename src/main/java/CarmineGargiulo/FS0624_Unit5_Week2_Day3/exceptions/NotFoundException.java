package CarmineGargiulo.FS0624_Unit5_Week2_Day3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Il record con id " + id + " non è stato trovato");
    }
}
