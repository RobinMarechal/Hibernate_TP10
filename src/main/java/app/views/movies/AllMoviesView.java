package app.views.movies;

import app.controllers.MovieController;
import app.controllers.ProducerController;
import app.models.Movie;
import app.models.Producer;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class AllMoviesView extends View
{
    private List<Movie> movies;

    /** JFX components */
    private TableView<Movie> moviesTable = new TableView<>();
    private LinkerTableColumn<Movie, Integer, Integer> idColumn;
    private LinkerTableColumn<Movie, String, Integer> titleColumn;
    private LinkerTableColumn<Movie, String, Integer> directorColumn;
    private LinkerTableColumn<Movie, Producer, Integer> producerColumn;

    public AllMoviesView (MovieController controller, List<Movie> movies)
    {
        super(controller);
        this.movies = movies;

        idColumn = new LinkerTableColumn<>(controller);
        titleColumn = new LinkerTableColumn<>(controller);
        directorColumn = new LinkerTableColumn<>(controller);
        producerColumn = new LinkerTableColumn<>(new ProducerController());

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setUpColumnFactories();
        setUpColumnTitles();
        setUpColumnDimensions();

        fitComponentToParent(moviesTable);
        moviesTable.getColumns().addAll(idColumn, titleColumn, directorColumn, producerColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));

        idColumn.prepareCellFactory();
        titleColumn.prepareCellFactory();
        directorColumn.prepareCellFactory();
        producerColumn.prepareCellFactory();
    }

    public void setUpColumnTitles ()
    {
        idColumn.setText("ID");
        titleColumn.setText("Title");
        directorColumn.setText("Director");
        producerColumn.setText("Producer");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
        titleColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        directorColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        producerColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.29);
    }

    @Override
    protected void display ()
    {
        moviesTable.getItems().addAll(movies);

        this.getChildren().add(moviesTable);
    }
}
