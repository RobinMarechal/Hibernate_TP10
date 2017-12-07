package app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Setup
{
    @Id
    private int id;

    @Basic
    private String description;

    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER, optional = false)
    private Scene scene;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "setup")
    private List<Clap> claps;

    public Setup ()
    {
    }

    public Setup (Scene scene)
    {
        this();
        this.scene = scene;
    }

    public Setup (Scene scene, String description)
    {
        this(scene);
        this.description = description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public int getId ()
    {
        return id;
    }

    public String getDescription ()
    {
        return description;
    }

    public Scene getScene ()
    {
        return scene;
    }

    public List<Clap> getClaps ()
    {
        return claps;
    }

    public void setScene (Scene scene)
    {
        // TODO
    }

    public void addClaps (Clap... clap)
    {
        // TODO
    }
}