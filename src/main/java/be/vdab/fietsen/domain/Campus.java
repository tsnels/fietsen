package be.vdab.fietsen.domain;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "campussen")
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Embedded
    private Adres adres;
    @ElementCollection
    @CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusId"))
    @OrderBy("fax")
    private Set<TelefoonNr> telefoonNrs;
    @OneToMany(mappedBy = "campus")
//    @JoinColumn(name = "campusId")
    @OrderBy("voornaam, familienaam")
    private Set<Docent> docenten;

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
        this.telefoonNrs = new LinkedHashSet<>();
        this.docenten = new LinkedHashSet<>();
    }

    protected Campus() {
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public boolean add (Docent docent) {
        var toegevoegd = docenten.add(docent);
        var oudeCampus = docent.getCampus();
        if (oudeCampus != null && oudeCampus != this) {
            oudeCampus.docenten.remove(docent);
        }
        if (this != oudeCampus) {
            docent.setCampus(this);
        }
        return toegevoegd;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public Set<TelefoonNr> getTelefoonNrs() {
        return Collections.unmodifiableSet(telefoonNrs);
    }



}
