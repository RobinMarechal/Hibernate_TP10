package app.views.movies;

import app.controllers.MovieController;
import app.controllers.ProducerController;
import app.models.Movie;
import app.models.Producer;
import app.models.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.mvc.views.View;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class AllMoviesView extends View<MovieController>
{
    private List<Movie> movies;

    /** JFX components */
    private TableView<Movie> moviesTable = new TableView<>();
    private LinkerTableColumn<Movie, Integer, Integer> idColumn;
    private LinkerTableColumn<Movie, String, Integer> titleColumn;
    private LinkerTableColumn<Movie, String, Integer> directorColumn;
    private LinkerTableColumn<Movie, Producer, Integer> producerColumn;
    private TableColumn<Movie, List<Scene>> scenesColumn;

    public AllMoviesView (MovieController controller, List<Movie> movies)
    {
        super(controller);
        this.movies = movies;

        idColumn = new LinkerTableColumn<>(controller);
        titleColumn = new LinkerTableColumn<>(controller);
        directorColumn = new LinkerTableColumn<>(controller);
        producerColumn = new LinkerTableColumn<>(new ProducerController());
        scenesColumn = new TableColumn<>();

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
        moviesTable.getColumns().addAll(idColumn, titleColumn, directorColumn, producerColumn, scenesColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));
        scenesColumn.setCellValueFactory(new PropertyValueFactory<>("scenes"));

        scenesColumn.setCellFactory(event -> new TableCell<Movie, List<Scene>>()
        {
            @Override
            protected void updateItem (List<Scene> item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText("" + item.size());
                }
            }
        });

        idColumn.prepareEventHandler();
        titleColumn.prepareEventHandler();
        directorColumn.prepareEventHandler();
        producerColumn.prepareEventHandler();
    }

    public void setUpColumnTitles ()
    {
        idColumn.setText("ID");
        titleColumn.setText("Title");
        directorColumn.setText("Director");
        producerColumn.setText("Producer");
        scenesColumn.setText("Scenes");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
        titleColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.27);
        directorColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.27);
        producerColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.27);
        scenesColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.09);
    }

    @Override
    protected void display ()
    {
        moviesTable.getItems().addAll(movies);

        this.getChildren().add(moviesTable);
    }
}
