package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producer extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();

    private ListProperty<Film> films = new SimpleListProperty<>();

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

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Film.class, mappedBy = "producer")
    public List<Film> getFilms ()
    {
        return films.get();
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty nameProperty ()
    {
        return name;
    }

    public ListProperty<Film> filmsProperty ()
    {
        return films;
    }

    public void setId (int id)
    {
        this.id.set(id);
    }

    public void setName (String name)
    {
        this.name.set(name);
    }

    public void setFilms (List<Film> films)
    {
        this.films.set(FXCollections.observableList(films));
    }
}
