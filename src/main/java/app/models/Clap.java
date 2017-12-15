package app.models;

import app.models.dao.ClapDAO;
import app.models.pk.ClapPrimaryKey;
import libs.mvc.models.Model;
import org.hibernate.Hibernate;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Clap is a class representing a whole shot of a scene according to a setup
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Clap extends Model<ClapPrimaryKey>
{
	/**
	 * ClapPrimaryKey of the Clap
	 */
    @EmbeddedId
    private ClapPrimaryKey primaryKey;

    /**
     * Duration of the Clap
     */
    @Basic
    private Integer duration;

    /**
     * Default constructor
     */
    public Clap ()
    {
    }

    /**
     * Constructor setting the setup's clap and its duration
     * @param setup Setup of the clap
     * @param duration Duration of the clap
     */
    public Clap (Setup setup, Integer duration)
    {
        this();
        this.duration = duration;
        this.primaryKey = new ClapPrimaryKey(setup);
    }

    /**
     * Getter for PrimaryKey attribute
     * @return PrimaryKey's Clap
     */
    public ClapPrimaryKey getPrimaryKey ()
    {
        return primaryKey;
    }

    /**
     * Getter for Duration attribute
     * @return Duration's Clap
     */
    public Integer getDuration ()
    {
        return duration;
    }

    /**
     * Setter for duration attribute
     * @param duration Duration's Clap
     */
    public void setDuration (Integer duration)
    {
        this.duration = duration;
    }

    /**
     * Getter for Setup of the clap
     * @return Setup's Clap
     */
    public Setup getSetup ()
    {
        return this.primaryKey.getSetup();
    }

    /**
     * Setter for the Setup of the Clap and create a new primaryKey
     * @param setup Setup's Clap
     */
    void setSetup (Setup setup)
    {
        ClapDAO dao = new ClapDAO();
        if(primaryKey != null)
            dao.remove(this);

        primaryKey = new ClapPrimaryKey(setup);
    }

    /**
     * Getter for Numero of the clap
     * @return Numero's Clap
     */
    public int getNumero ()
    {
        return this.primaryKey.getNumero();
    }

    /**
     * Getter for primaryKey attribute
     * @return PrimaryKey's Clap
     */
    @Transient
    @Override
    public ClapPrimaryKey getId ()
    {
        return getPrimaryKey();
    }

	/**
     * Check if the two objects are equals
     * @param o Object to be compared
     * @return true if they are equals, false instead
	 */
    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        o = Hibernate.unproxy(o);
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Clap clap = (Clap) o;

        return primaryKey.equals(clap.primaryKey);
    }

    /**
     * Get the hashcode of the primaryKey's clap
     * @return hashcode of the primaryKey's clap
     */
    @Override
    public int hashCode ()
    {
        return primaryKey.hashCode();
    }
}
