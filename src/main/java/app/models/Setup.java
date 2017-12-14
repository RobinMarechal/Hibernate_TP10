package app.models;

import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Setup extends Model<Integer>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String description;

    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER, optional = false)
    private Scene scene;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "setup")
    private List<Clap> claps;

    public Setup ()
    {
        claps = new ArrayList<>();
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

    @Override
    public Integer getId ()
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

    void setScene (Scene scene)
    {
        this.scene = scene;
    }

    public void addClaps (Clap... claps)
    {
        for (Clap clap : claps) {
            this.claps.add(clap);
            clap.setSetup(this);
        }
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

        Setup setup = (Setup) o;

        return id == setup.id && id != null && id != 0;
    }

    @Override
    public int hashCode ()
    {
        return id;
    }
}
