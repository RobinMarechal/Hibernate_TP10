package app.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producer extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();

    private ListProperty<Movie> movies = new SimpleListProperty<>();

    public Producer()
    {
        super();
    }

    public Producer (String name)
    {
        this();
        setName(name);
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.get();
    }

    @Column
    public String getName ()
    {
        return name.get();
    }

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Movie.class, mappedBy = "producer")
    public List<Movie> getMovies ()
    {
        return movies.get();
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty nameProperty ()
    {
        return name;
    }

    public ListProperty<Movie> filmsProperty ()
    {
        return movies;
    }

    public void setId (int id)
    {
        this.id.set(id);
    }

    public void setName (String name)
    {
        this.name.set(name);
    }

    public void setMovies (List<Movie> films)
    {
        this.movies.set(FXCollections.observableList(films));
    }

    @Override
    public String toString ()
    {
        return getName();
    }
}
