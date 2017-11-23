package models;

import javafx.beans.property.*;

import javax.persistence.*;

@Entity
@Table (name = "typologies")
public class Typology
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty nbFish = new SimpleIntegerProperty(this, "nbFish");

    private StringProperty species = new SimpleStringProperty(this, "species");
    private StringProperty depth = new SimpleStringProperty(this, "depth");

    private FloatProperty waterQuantity = new SimpleFloatProperty(this, "waterQuantity");

    private ObjectProperty<Group> group = new SimpleObjectProperty<>(this, "group");


    // -----------------------
    // Getters
    // -----------------------

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.getValue();
    }

    @Column
    public int getNbFish ()
    {
        return nbFish.getValue();
    }

    @Column
    public String getSpecies ()
    {
        return species.getValueSafe();
    }

    @Column
    public float getWaterQuantity ()
    {
        return waterQuantity.getValue();
    }

    @Column
    public String getDepth ()
    {
        return depth.getValueSafe();
    }

    @ManyToOne
    public Group getGroup ()
    {
        return group.getValue();
    }
    // -----------------------
    // Setters
    // -----------------------


    public Typology setId (int id)
    {
        if (this.id.getValue() == 0) {
            this.id.set(id);
        }
        return this;
    }

    public void setNbFish (int nbFish)
    {
        this.nbFish.set(nbFish);
    }

    public void setSpecies (String species)
    {
        this.species.set(species);
    }

    public void setDepth (String depth)
    {
        this.depth.set(depth);
    }

    public void setWaterQuantity (float waterQuantity)
    {
        this.waterQuantity.set(waterQuantity);
    }

    Typology setGroup (Group group)
    {
        this.group.set(group);
        return this;
    }

    // -----------------------
    // Properties
    // -----------------------

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public IntegerProperty nbFishProperty ()
    {
        return nbFish;
    }

    public StringProperty speciesProperty ()
    {
        return species;
    }

    public StringProperty depthProperty ()
    {
        return depth;
    }

    public FloatProperty waterQuantityProperty ()
    {
        return waterQuantity;
    }

    public ObjectProperty<Group> groupProperty ()
    {
        return group;
    }


    @Override
    public String toString ()
    {
        return "Typology{" + "id=" + getId() + ", nbFish=" + getNbFish() + ", species=" + getSpecies() + ", depth=" + getDepth() + ", waterQuantity=" +
                getWaterQuantity() + "," + " " + "group=" + getGroup().getId() + '}';
    }
}
