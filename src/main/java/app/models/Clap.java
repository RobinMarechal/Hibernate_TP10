package app.models;

import app.models.pk.ClapPrimaryKey;
import libs.mvc.models.Model;

import javax.persistence.*;
import java.time.Duration;

@Entity
public class Clap extends Model<ClapPrimaryKey>
{
    @EmbeddedId
    private ClapPrimaryKey primaryKey;

    @Basic
    private Duration duration;

    @ManyToOne (cascade = CascadeType.DETACH)
    private Setup setup;

    public Clap ()
    {
    }

    public Clap (Duration duration, Setup setup)
    {
        this();
        this.duration = duration;
        this.setup = setup;
    }

    public ClapPrimaryKey getPrimaryKey ()
    {
        return primaryKey;
    }

    public Duration getDuration ()
    {
        return duration;
    }

    public void setDuration (Duration duration)
    {
        this.duration = duration;
    }

    public Setup getSetup ()
    {
        return setup;
    }

    void setSetup (Setup setup)
    {
        this.setup = setup;
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
