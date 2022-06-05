package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Cursus;
import org.hibernate.engine.spi.EntityEntry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public class CursusRepository {

    private final EntityManager manager;

    public CursusRepository(EntityManager manager) {
        this.manager = manager;
    }

    public Optional<Cursus> findById(long id) {
        return Optional.ofNullable(manager.find(Cursus.class, id));
    }

    public void create(Cursus cursus){
        manager.persist(cursus);
    }
}
