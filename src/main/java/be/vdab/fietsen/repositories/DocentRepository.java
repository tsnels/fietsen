package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.projections.IdEnEmailAdres;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class DocentRepository {
    private final EntityManager manager;

    public DocentRepository(EntityManager manager) {
        this.manager = manager;
    }

    public Optional<Docent> findById(long id) {
        return Optional.ofNullable(manager.find(Docent.class, id));
    }

    public void create(Docent docent){
        manager.persist(docent);
    }

    public void delete(long id) {
        findById(id).ifPresent(docent -> manager.remove(docent));
    }

    public List<Docent> findAll() {
        return manager.createQuery(
                """
                    select d
                    from Docent d
                    order by d.wedde
                """,
                Docent.class).getResultList();
    }

    public List<Docent> findWeddeBetween(BigDecimal van, BigDecimal tot) {
        return manager.createQuery(
                """
                    select d
                    from Docent d
                    where d.wedde between :van and :tot
                """, Docent.class).setParameter("van", van).
                setParameter("tot", tot).getResultList();
    }

    public List<String> findEmailAdressen() {
        return manager.createQuery(
                """
                    select d.emailAdres
                    from Docent d
                """, String.class).getResultList();
    }

    public List<IdEnEmailAdres> findIdsEnEmailAdressen() {
        return manager.createQuery(
                """
                    select new be.vdab.fietsen.projections.IdEnEmailAdres(d.id, d.emailAdres)
                    from Docent d
                """, IdEnEmailAdres.class).getResultList();
    }

    public BigDecimal findGrootsteWedde() {
        return manager.createQuery(
                """
                    select max(d.wedde)
                    from Docent d
                """, BigDecimal.class).getSingleResult();
    }

}