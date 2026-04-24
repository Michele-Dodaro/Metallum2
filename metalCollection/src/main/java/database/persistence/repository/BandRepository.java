package database.persistence.repository;

import database.persistence.Entity.Band;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped

public class BandRepository implements PanacheRepositoryBase<Band, Integer> {
    public Optional<Band> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

}
