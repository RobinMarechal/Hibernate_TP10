package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "groups")
public class Group
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty nbMeals = new SimpleIntegerProperty(this, "nbMeals");

    private ListProperty<Typology> typologies = new SimpleListProperty<>(this, "typologies");

    private ObjectProperty<Owner> owner = new SimpleObjectProperty<>(this, "owner");
    private ObjectProperty<Tank> tank = new SimpleObjectProperty<>(this, "tank");

    public Group ()
    {
        this.typologies.setValue(FXCollections.observableArrayList());
    }

    // ------------------------------
    // Getters
    // ------------------------------

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.getValue();
    }

    @Column
    public int getNbMeals ()
    {
        return nbMeals.getValue();
    }

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Typology.class, mappedBy = "group")
    public List<Typology> getTypologies ()
    {
        return typologies;
    }

    @ManyToOne
    public Owner getOwner ()
    {
        return owner.getValue();
    }

    @ManyToOne
    public Tank getTank ()
    {
        return tank.getValue();
    }


    // ------------------------------
    // Propeties
    // ------------------------------

    public ListProperty<Typology> typologiesProperty ()
    {
        return typologies;
    }

    public ObjectProperty<Owner> ownerProperty ()
    {
        return owner;
    }

    public ObjectProperty<Tank> tankProperty ()
    {
        return tank;
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public IntegerProperty nbMealsProperty ()
    {
        return nbMeals;
    }


    // ------------------------------
    // Setters
    // ------------------------------


    Group setTank (Tank tank)
    {
        this.tank.setValue(tank);
        return this;
    }

    public Group setNbMeals (int nbMeals)
    {
        this.nbMeals.setValue(nbMeals);
        return this;
    }

    Group setOwner (Owner owner)
    {
        this.owner.setValue(owner);
        return this;
    }

    public Group addTypologies (Typology... typologies)
    {
        ObservableList<Typology> list = this.typologies.getValue();
        for (Typology typology : typologies) {
            list.add(typology);
            typology.setGroup(this);
        }
        return this;
    }

    // ------------------------------
    // Should not be called
    // ------------------------------

    /**
     * Set the list of typologies, only if it's not already been defined
     *
     * @param typologies whatever you want
     * @return this
     */
    @Deprecated
    public Group setTypologies (List<Typology> typologies)
    {
        if (this.typologies.getValue() == null || this.typologies.getValue().isEmpty()) {
            this.typologies.setValue(FXCollections.observableList(typologies));
            typologies.forEach(typology -> typology.setGroup(this));
        }
        return this;
    }

    /**
     * Set the id, only if it's not already been defined
     *
     * @param id the id
     * @return this
     */
    @Deprecated
    public Group setId (int id)
    {
        if (this.id.getValue() == 0) {
            this.id.setValue(id);
        }
        return this;
    }

    // -----------------------
    // Others
    // -----------------------

    /**
     * Check if all the fishes of this groups are of the same species
     *
     * @return true if all the fishes of this group are of the same species, false otherwise
     */
    public boolean areFishesAllOfTheSameSpecies ()
    {
        List<Typology> fishes = this.getTypologies();
        if (!fishes.isEmpty()) {
            String species = fishes.get(0).getSpecies();
            for (int i = 1; i < fishes.size(); i++) {
                if (!species.equals(fishes.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Check a given tank has sufficient water capacity to welcome this group
     * @param tank the tank to analyze
     * @return true if the tank has sufficient capacity to welcome this group
     */
    public boolean canBePlacedIn(Tank tank)
    {
        return tank.hasSufficientCapacityFor(this);
    }


    @Override
    public String toString ()
    {
        return "Group{" + "id=" + getId() + ", nbMeals=" + getNbMeals() + ", typologies=" + getTypologies() + ", owner=" + getOwner() + ", tank=" +
                getTypologies() + '}';
    }
}
