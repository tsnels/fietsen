package be.vdab.fietsen.domain;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "docenten")

public class Docent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private String emailAdres;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @ElementCollection
    @CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentId"))
    @Column(name = "bijnaam")
    private Set<String> bijnamen;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campusId")
    private Campus campus;
    @ManyToMany(mappedBy = "docenten")
    private Set<Verantwoordelijkheid> verantwoordelijkheden = new LinkedHashSet<>();


    public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht, Campus campus) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
        this.bijnamen = new LinkedHashSet<>();

        setCampus(campus);
    }

    protected Docent() {
    }

    public void opslag(BigDecimal percentage) {
        if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        var factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        if (!campus.getDocenten().contains(this)) {
            campus.add(this);
        }
        this.campus = campus;
    }

    public boolean add (Verantwoordelijkheid verantwoordelijkheid) {
        var toegevoegd = verantwoordelijkheden.add(verantwoordelijkheid);
        if ( ! verantwoordelijkheid.getDocenten().contains(this)) {
            verantwoordelijkheid.add(this);
        }
        return toegevoegd;
    }

    public boolean remove (Verantwoordelijkheid verantwoordelijkheid){
        var verwijderd = verantwoordelijkheden.remove(verantwoordelijkheid);
        if (verantwoordelijkheid.getDocenten().contains(this)) {
            verantwoordelijkheid.remove(this);
        }
        return verwijderd;
    }

    public Set<Verantwoordelijkheid> getVerantwoordelijkheden() {
        return Collections.unmodifiableSet(verantwoordelijkheden);
    }


    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public boolean addBijnaam(String bijnaam) {
        if (bijnaam.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return bijnamen.add(bijnaam);
    }

    public boolean removeBijnaam(String bijnaam) {
        return bijnamen.remove(bijnaam);
    }

    public Set<String> getBijnamen() {
        return Collections.unmodifiableSet(bijnamen);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Docent docent && emailAdres.equalsIgnoreCase(docent.emailAdres);
    }

    @Override
    public int hashCode() {
        return emailAdres == null ? 0 : emailAdres.toLowerCase().hashCode();
    }
}
