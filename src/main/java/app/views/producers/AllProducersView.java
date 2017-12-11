package app.views.producers;

import app.controllers.ProducerController;
import app.models.Movie;
import app.models.Producer;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class AllProducersView extends View
{
    private List<Producer> movies;

    /** JFX components */
    private TableView<Producer> moviesTable;
    private LinkerTableColumn<Producer, Integer, Integer> idColumn;
    private LinkerTableColumn<Producer, String, Integer> nameColumn;
    private TableColumn<Producer, List<Movie>> nbMoviesColumn;

    public AllProducersView (ProducerController controller, List<Producer> movies)
    {
        super(controller);
        this.movies = movies;

        moviesTable = new TableView<>();
        idColumn = new LinkerTableColumn<>(controller);
        nameColumn = new LinkerTableColumn<>(controller);
        nbMoviesColumn = new TableColumn<>();

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
        moviesTable.getColumns().addAll(idColumn, nameColumn, nbMoviesColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nbMoviesColumn.setCellValueFactory(new PropertyValueFactory<>("movies"));

        nbMoviesColumn.setCellFactory(param -> new TableCell<Producer, List<Movie>>()
        {
            @Override
            protected void updateItem (List<Movie> item, boolean empty)
            {
                super.updateItem(item, empty);
                textProperty().unbind();

                if (item != null && !empty) {
                    setText(item.size() + "");
                }
            }
        });

        idColumn.prepareEventHandler();
        nameColumn.prepareEventHandler();
    }

    public void setUpColumnTitles ()
    {
        idColumn.setText("ID");
        nameColumn.setText("Name");
        nbMoviesColumn.setText("Movies");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.20);
        nameColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.40);
        nbMoviesColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.40);
    }

    @Override
    protected void display ()
    {
        moviesTable.getItems().addAll(movies);
        this.getChildren().add(moviesTable);
    }
}