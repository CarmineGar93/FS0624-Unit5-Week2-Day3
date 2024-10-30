package CarmineGargiulo.FS0624_Unit5_Week2_Day3.repositories;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, UUID> {
    boolean existsByEmail(String email);
}
