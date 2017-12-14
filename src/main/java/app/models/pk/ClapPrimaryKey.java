package app.models.pk;

import app.models.Setup;
import app.models.exceptions.ModelException;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ClapPrimaryKey implements Serializable
{
    @Basic
    @GeneratedValue (strategy = GenerationType.TABLE)
    private int number;

    @ManyToOne
    private Setup setup;

    public ClapPrimaryKey (Setup setup, int number)
    {
        this(setup);
        if (number <= 0) {
            this.number = number;
        }
    }

    public ClapPrimaryKey (Setup setup)
    {
        this.setup = setup;
    }

    public ClapPrimaryKey ()
    {
    }

    public int getNumero ()
    {
        return number;
    }

    public Setup getSetup ()
    {
        return setup;
    }

    /**
     * Set the clap's number.
     *
     * @param number the number of the clap
     * @throws ModelException if the number has already been set
     * @<b>WARNING: Once the number has been set, you can't change it. Doing so will throw a {@link ModelException}</b>
     */
    public void setNumero (int number)
    {
        if (this.number != 0) {
            throw new ModelException("Once the number has been set, you can't change it.");
        }

        this.number = number;
    }

    public void setSetup (Setup setup)
    {
        if (this.setup != null) {
            throw new ModelException("One the setup has been set, you can't change it");
        }

        this.setup = setup;
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClapPrimaryKey that = (ClapPrimaryKey) o;
        return number != 0 && number == that.number && setup != null && setup.equals(that.setup);
    }

    @Override
    public int hashCode ()
    {
        int hashcode = number;
        if (setup != null) {
            hashcode += 31 * setup.hashCode();
        }

        return hashcode;
    }
}
