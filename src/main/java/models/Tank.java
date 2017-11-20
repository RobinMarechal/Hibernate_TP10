package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tanks")
public class Tank
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double capacity;
    private String material;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tank")
    private List<Group> groups;

    /**
     * A tank can contain only one group <br>
     * If the tank already contains a group, it replaces it
     *
     * @param group the new group in the tank
     * @return this
     */
    public Tank setGroup (Group group)
    {
        if (groups.size() == 0)
        {
            this.groups.add(group);
        }
        else
        {
            this.groups.remove(0);
            this.groups.add(group);
        }

        return this;
    }

    public Tank removeGroup ()
    {
        if (groups.size() == 1)
        {
            groups.remove(0);
        }

        return this;
    }

    public Tank setCapacity (double capacity)
    {
        this.capacity = capacity;
        return this;
    }

    public Tank setMaterial (String material)
    {
        this.material = material;
        return this;
    }

    public int getId ()
    {
        return id;
    }

    public double getCapacity ()
    {
        return capacity;
    }

    public String getMaterial ()
    {
        return material;
    }

    public Group getGroup ()
    {
        return groups.size() == 0 ? null : groups.get(0);
    }
}
