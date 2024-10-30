package CarmineGargiulo.FS0624_Unit5_Week2_Day3.repositories;

import CarmineGargiulo.FS0624_Unit5_Week2_Day3.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, UUID> {
}
