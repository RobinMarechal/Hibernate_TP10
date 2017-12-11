package app.models.pk;

import app.models.exceptions.ModelException;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClapPrimaryKey implements Serializable
{
    @Basic
    private int number;

    @Basic
    private int setupId;

    public ClapPrimaryKey (int number, int setupId)
    {
        this.number = number;
        this.setupId = setupId;
    }

    public ClapPrimaryKey ()
    {
    }

    public int getNumero ()
    {
        return number;
    }

    public int getSetupId ()
    {
        return setupId;
    }

    /**
     * Set the clap's number.
     * @<b>WARNING: Once the number has been set, you can't change it. Doing so will throw a {@link ModelException}</b>
     *
     * @param number the number of the clap
     * @throws ModelException if the number has already been set
     */
    public void setNumero (int number)
    {
        if (this.number != -1) {
            throw new ModelException("Once the number has been set, you can't change it.");
        }

        this.number = number;
    }

    public void setSetupId (int setupId)
    {
        if (this.setupId == -1) {
            throw new ModelException("One the setupId has been set, you can't change it");
        }

        this.setupId = setupId;
    }
}
