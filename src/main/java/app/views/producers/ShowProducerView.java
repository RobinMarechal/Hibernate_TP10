package app.views.producers;

import app.controllers.MovieController;
import app.controllers.ProducerController;
import app.models.Movie;
import app.models.Producer;
import app.models.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;

import java.util.List;

public class ShowProducerView extends View<ProducerController>
{
    private final Producer producer;

    private final MovieController movieController = new MovieController();

    private BorderPane borderPane = new BorderPane();

    private VBox vbox = new VBox();
    private Label nameLabel = new Label();
    private Label idLabel = new Label();
    private Label nbMoviesLabel = new Label();

    private TableView<Movie> moviesTable = new TableView<>();
    private LinkerTableColumn<Movie, Integer, Integer> idColumn;
    private LinkerTableColumn<Movie, String, Integer> titleColumn;
    private TableColumn<Movie, String> directorColumn;
    private TableColumn<Movie, List<Scene>> scenesColumn;

    public ShowProducerView (ProducerController controller, Producer producer)
    {
        super(controller);
        this.producer = producer;

        idColumn = new LinkerTableColumn<>(movieController);
        titleColumn = new LinkerTableColumn<>(movieController);
        directorColumn = new TableColumn<>();
        scenesColumn = new TableColumn<>();

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setAnchorOfComponent(borderPane, 5, 5, 5, 5);
        setupTopPart();
        setupBottomPart();
    }

    private void setupBottomPart ()
    {
        setupColumnHeaders();
        setupColumnFactories();
        setupColumnDimension();


        moviesTable.getColumns().addAll(idColumn, titleColumn, directorColumn, scenesColumn);
        borderPane.setCenter(moviesTable);
    }

    private void setupColumnHeaders ()
    {
        idColumn.setText("ID");
        titleColumn.setText("Title");
        directorColumn.setText("Director");
        scenesColumn.setText("Scenes");
    }

    private void setupColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
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
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(idColumn, moviesTable, 15);
        setSizeOfColumnInTable(titleColumn, moviesTable, 35);
        setSizeOfColumnInTable(directorColumn, moviesTable, 35);
        setSizeOfColumnInTable(scenesColumn, moviesTable, 14);
    }

    private void setupTopPart ()
    {
        nameLabel.setText(producer.getName());
        idLabel.setText("ID: " + producer.getId());
        nbMoviesLabel.setText("Movies:");

        vbox.getChildren().addAll(nameLabel/*, idLabel*/, nbMoviesLabel);

        borderPane.setTop(vbox);

        nameLabel.getStyleClass().add("h2");
        idLabel.getStyleClass().addAll("p", "text-italic");
        nbMoviesLabel.getStyleClass().addAll("p");

        nameLabel.setMaxWidth(Double.MAX_VALUE);
        idLabel.setMaxWidth(Double.MAX_VALUE);
        nbMoviesLabel.setMaxWidth(Double.MAX_VALUE);

        nameLabel.setAlignment(Pos.CENTER);
        idLabel.setAlignment(Pos.CENTER);
        nbMoviesLabel.setAlignment(Pos.CENTER);
    }

    @Override
    protected void display ()
    {
        moviesTable.getItems().addAll(producer.getMovies());
        this.addComponents(borderPane);
    }
}
