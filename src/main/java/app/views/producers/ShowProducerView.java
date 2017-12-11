package app.views.producers;

import app.controllers.MovieController;
import app.controllers.ProducerController;
import app.models.Movie;
import app.models.Producer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;

public class ShowProducerView extends View
{
    private final Producer producer;

    private final MovieController movieController = new MovieController();

    private BorderPane borderPane = new BorderPane();

    private VBox vbox = new VBox();
    private Label nameLabel = new Label();
    private Label idLabel = new Label();
    private Label nbMoviesLabel = new Label();

    private TableView<Movie> moviesTable = new TableView<>();
    private LinkerTableColumn<Movie, Integer, Integer> idColumn ;
    private LinkerTableColumn<Movie, String, Integer> titleColumn ;
    private LinkerTableColumn<Movie, String, Integer> directorColumn ;

    public ShowProducerView (ProducerController controller, Producer producer)
    {
        super(controller);
        this.producer = producer;

        idColumn = new LinkerTableColumn<>(movieController);
        titleColumn = new LinkerTableColumn<>(movieController);
        directorColumn = new LinkerTableColumn<>(movieController);

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


        moviesTable.getColumns().addAll(idColumn, titleColumn, directorColumn);
        borderPane.setBottom(moviesTable);
    }

    private void setupColumnHeaders()
    {
        idColumn.setText("ID");
        titleColumn.setText("Title");
        directorColumn.setText("Director");
    }

    private void setupColumnFactories()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
    }

    private void setupColumnDimension()
    {
        setSizeOfColumnInTable(idColumn, moviesTable, 15);
        setSizeOfColumnInTable(titleColumn, moviesTable, 35);
        setSizeOfColumnInTable(directorColumn, moviesTable, 35);
    }

    private void setupTopPart ()
    {
        nameLabel.setText(producer.getName());
        idLabel.setText("ID: " + producer.getId());
        nbMoviesLabel.setText("Movies: " + producer.getMovies().size());

        vbox.getChildren().addAll(nameLabel, idLabel, nbMoviesLabel);

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
