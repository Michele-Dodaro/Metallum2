package database.persistence.repository;

import database.persistence.Entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public Optional<User> findByUsername(String username) {
        String queryText = "SELECT u" +
                " FROM User u " +
                " WHERE u.username = :username";
        return find(queryText, Map.of("username", username)).firstResultOptional();
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }

}





