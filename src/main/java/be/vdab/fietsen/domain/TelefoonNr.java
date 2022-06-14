package be.vdab.fietsen.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Access(AccessType.FIELD)
public class TelefoonNr {

    private String nummer;
    private boolean fax;
    private String opmerking;

    public TelefoonNr(String nummer, boolean fax, String opmerking) {
        this.nummer = nummer;
        this.fax = fax;
        this.opmerking = opmerking;
    }

    protected TelefoonNr() {

    }

    @Override
    public boolean equals(Object object) {
        return object instanceof TelefoonNr telefoonNr && nummer.equalsIgnoreCase(telefoonNr.nummer);
    }

    @Override
    public int hashCode() {
        return nummer.toUpperCase().hashCode();
    }
}