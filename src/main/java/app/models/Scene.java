package app.models;

import app.models.exceptions.ModelException;
import libs.DayTime;
import libs.PlaceType;
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

    private DayTime dayTime = DayTime.NULL;

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
        this(movie, place, null);
    }

    public Scene (Movie movie, Place place, DayTime dayTime)
    {
        this(movie);
        if(place.getType() == PlaceType.THEATRE)
            setPlace((Theatre) place);
        else
            setPlace((ExternalPlace) place, dayTime);
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

    void setDayTime (DayTime dayTime)
    {
        this.dayTime = dayTime;
    }

    void setMovie (Movie movie)
    {
        this.movie = movie;
    }

    public void setPlace (Theatre theatre)
    {
        if (theatre != null && !this.place.equals(theatre)) {
            this.place = theatre;
            theatre.addScenes(this);
        }
    }

    public void setPlace (ExternalPlace externalPlace)
    {
        setPlace(externalPlace, DayTime.DAY);
    }

    public void setPlace (ExternalPlace externalPlace, DayTime dayTime)
    {
        if (externalPlace != null && !this.place.equals(externalPlace)) {
            if(dayTime == null)
                throw new ModelException("The daytime must not be null for external places");

            this.dayTime = dayTime;
            this.place = externalPlace;
            externalPlace.addScenes(this);

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
