package eu.kielczewski.example.repository;

import eu.kielczewski.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
