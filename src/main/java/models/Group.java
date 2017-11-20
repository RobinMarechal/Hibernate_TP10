package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nbMeals;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Typology.class, mappedBy = "group")
    private List<Typology> typologies;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Tank tank;


    public int getId ()
    {
        return id;
    }

    public Group setId (int id)
    {
        this.id = id;
        return this;
    }

    public int getNbMeals ()
    {
        return nbMeals;
    }

    public List<Typology> getTypologies ()
    {
        return typologies;
    }

    public Owner getOwner ()
    {
        return owner;
    }

    public Tank getTank()
    {
        return tank;
    }

    public Group setNbMeals (int nbMeals)
    {
        this.nbMeals = nbMeals;
        return this;
    }

    public Group setOwner (Owner owner)
    {
        this.owner = owner;
        return this;
    }

    public Group addTypologies(Typology... typology)
    {
        // TODO
        return this;
    }

}
