package app.models;

import app.models.dao.ClapDAO;
import app.models.pk.ClapPrimaryKey;
import libs.mvc.models.Model;
import org.hibernate.Hibernate;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Clap extends Model<ClapPrimaryKey>
{
    @EmbeddedId
    private ClapPrimaryKey primaryKey;

    @Basic
    private Integer duration;

    public Clap ()
    {
    }

    public Clap (Setup setup, Integer duration)
    {
        this();
        this.duration = duration;
        this.primaryKey = new ClapPrimaryKey(setup);
    }

    public ClapPrimaryKey getPrimaryKey ()
    {
        return primaryKey;
    }

    public Integer getDuration ()
    {
        return duration;
    }

    public void setDuration (Integer duration)
    {
        this.duration = duration;
    }

    public Setup getSetup ()
    {
        return this.primaryKey.getSetup();
    }

    void setSetup (Setup setup)
    {
        ClapDAO dao = new ClapDAO();
        if(primaryKey != null)
            dao.remove(this);

        primaryKey = new ClapPrimaryKey(setup);
    }

    public int getNumero ()
    {
        return this.primaryKey.getNumero();
    }

    @Transient
    @Override
    public ClapPrimaryKey getId ()
    {
        return getPrimaryKey();
    }


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

    @Override
    public int hashCode ()
    {
        return primaryKey.hashCode();
    }
}
