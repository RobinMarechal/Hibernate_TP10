package models;

import javax.persistence.*;

@Entity
@Table(name = "typologies")
public class Typology
{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private int nbFish;

    private String species;

    private float waterQuantity;

    private String depth;

    @ManyToOne
    private Group group;

    public Typology setNbFish (int nbFish)
    {
        this.nbFish = nbFish;
        return this;
    }

    public Typology setSpecies (String species)
    {
        this.species = species;
        return this;
    }

    public Typology setWaterQuantity (float waterQuantity)
    {
        this.waterQuantity = waterQuantity;
        return this;
    }

    public Typology setDepth (String depth)
    {
        this.depth = depth;
        return this;
    }

    public Typology setGroup (Group group)
    {
        this.group = group;
        return this;
    }

    public int getId ()
    {
        return id;
    }

    public int getNbFish ()
    {
        return nbFish;
    }

    public String getSpecies ()
    {
        return species;
    }

    public float getWaterQuantity ()
    {
        return waterQuantity;
    }

    public String getDepth ()
    {
        return depth;
    }

    public Group getGroup ()
    {
        return group;
    }
}
