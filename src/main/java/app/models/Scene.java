package app.models;

import libs.DayTime;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Scene extends Model<Integer>
{
    @Id
    private int id;

    @Basic
    private String description;

    @Basic
    private DayTime dayTime;

    @ManyToOne (optional = false)
    private Movie movie;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scene")
    private List<Setup> setups;

    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Place place;

    public Scene ()
    {
    }

    public Scene (Movie movie)
    {
        this();
        this.movie = movie;
    }

    public Scene (Movie movie, Place place)
    {
        this(movie);
        this.place = place;
    }

    public Scene (Movie movie, Place place, DayTime dayTime)
    {
        this(movie, place);
        this.place = place;
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

    public void setMovie (Movie movie)
    {
        // TODO
    }

    public void setPlace (Place place)
    {
        // TODO
    }

    public void addSetups(Setup... setup)
    {
        // TODO
    }
}
