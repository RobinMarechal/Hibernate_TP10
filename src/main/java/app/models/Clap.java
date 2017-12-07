package app.models;

import app.models.pk.ClapPrimaryKey;

import javax.persistence.*;
import java.time.Duration;

@Entity
public class Clap
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

    public void setSetup (Setup setup)
    {
        // TODO
    }
}
