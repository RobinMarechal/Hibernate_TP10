package app.models;

import libs.DayTime;
import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scene extends Model<Integer>
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String description;

    @Basic
    @Enumerated (EnumType.STRING)
    private DayTime dayTime = DayTime.DAY;

    @ManyToOne (optional = false)
    private Movie movie;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scene")
    private List<Setup> setups;

    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Place place;

    public Scene ()
    {
        setups = new ArrayList<>();
    }

    public Scene (Movie movie)
    {
        this();
        setMovie(movie);
    }

    public Scene (Movie movie, Place place)
    {
        this(movie);
        setPlace(place);
    }

    public Scene (Movie movie, Place place, DayTime dayTime)
    {
        this(movie, place);
        this.dayTime = dayTime;
    }


    public Scene (Movie movie, Place place, DayTime dayTime, String description)
    {
        this(movie, place, dayTime);
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

    public DayTime getDayTime ()
    {
        return dayTime;
    }

    public Movie getMovie ()
    {
        return movie;
    }

    public List<Setup> getSetups ()
    {
        return setups;
    }

    public Place getPlace ()
    {
        return place;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public void setDayTime (DayTime dayTime)
    {
        this.dayTime = dayTime;
    }

    void setMovie (Movie movie)
    {
        this.movie = movie;
    }

    public void setPlace (Place place)
    {
        if (place != null && this.place != place) {
            this.place = place;
            place.addScenes(this);
        }
    }

    public void addSetups (Setup... setups)
    {
        for (Setup setup : setups) {
            this.setups.add(setup);
            setup.setScene(this);
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

        Scene scene = (Scene) o;

        return id == scene.id && id != 0;
    }

    @Override
    public int hashCode ()
    {
        return id;
    }
}
