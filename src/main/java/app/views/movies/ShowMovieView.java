package app.views.movies;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.ProducerController;
import app.controllers.SceneController;
import app.models.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import libs.DayTime;
import libs.mvc.Model;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;

import java.util.ArrayList;
import java.util.List;

public class ShowMovieView extends View
{
    private final Movie movie;
    private final Producer producer;

    private final SceneController sceneController;
    private final PlaceController placeController;

    private BorderPane borderPane;

    private VBox vbox;
    private Label titleLabel;
    private Label idLabel;
    private Label nbScenesLabel;
    private Label directorLabel;
    private Label producerLabel; // Handle Click

    private TableView<SceneRow> scenesTable;
    private LinkerTableColumn<SceneRow, Integer, Integer> idColumn;
    private TableColumn<SceneRow, Integer> nbSetupsColumn;
    private TableColumn<SceneRow, Boolean> isOutsideColumn; // Handle click event
    private TableColumn<SceneRow, String> dayTimeColumn; // Handle click Event
    private LinkerTableColumn<SceneRow, String, Integer> placeNameColumn;

    public ShowMovieView (MovieController controller, Movie movie)
    {
        super(controller);
        this.movie = movie;
        this.producer = movie.getProducer();

        sceneController = new SceneController();
        placeController = new PlaceController();

        borderPane = new BorderPane();

        vbox = new VBox();
        titleLabel = new Label();
        idLabel = new Label();
        nbScenesLabel = new Label();
        directorLabel = new Label();
        producerLabel = new Label();

        scenesTable = new TableView<>();
        idColumn = new LinkerTableColumn<>(controller);
        placeNameColumn = new LinkerTableColumn<>(placeController);
        nbSetupsColumn = new TableColumn<>();
        isOutsideColumn = new TableColumn<>();
        dayTimeColumn = new TableColumn<>();

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setAnchorOfComponent(borderPane, 5, 5, 5, 5);
        setupTopPart();
        setupBottomPart();
        prepareEventHandlers();
    }

    public void prepareEventHandlers ()
    {
        producerLabel.getStyleClass().add("link");
        producerLabel.setOnMouseClicked(event -> new ProducerController().show(movie.getProducer().getId()));
    }

    private void setupBottomPart ()
    {
        setupColumnHeaders();
        setupColumnFactories();
        setupColumnDimension();


        scenesTable.getColumns().addAll(idColumn, nbSetupsColumn, isOutsideColumn, dayTimeColumn, placeNameColumn);
        borderPane.setBottom(scenesTable);
    }

    private void setupColumnHeaders ()
    {
        idColumn.setText("ID");
        nbSetupsColumn.setText("Setups");
        isOutsideColumn.setText("Is Outside");
        dayTimeColumn.setText("Time of Day");
        placeNameColumn.setText("Place");
    }

    private void setupColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nbSetupsColumn.setCellValueFactory(new PropertyValueFactory<>("NbSetups"));
        isOutsideColumn.setCellValueFactory(new PropertyValueFactory<>("getOutside"));
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory<>("getDayTime"));
        placeNameColumn.setCellValueFactory(new PropertyValueFactory<>("getPlaceName"));
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(idColumn, scenesTable, 15);
        setSizeOfColumnInTable(nbSetupsColumn, scenesTable, 15);
        setSizeOfColumnInTable(isOutsideColumn, scenesTable, 15);
        setSizeOfColumnInTable(dayTimeColumn, scenesTable, 15);
        setSizeOfColumnInTable(placeNameColumn, scenesTable, 40);
    }

    private void setupTopPart ()
    {
        // Text
        titleLabel.setText(movie.getTitle());
        idLabel.setText("ID: " + movie.getId());
        nbScenesLabel.setText("Scenes (" + movie.getScenes().size() + "): ");
        directorLabel.setText("Director: " + movie.getDirector());

        vbox.getChildren().addAll(titleLabel, idLabel, directorLabel);

        if (producer != null) {
            producerLabel.setText("Produced by: " + this.producer.getName());
            vbox.getChildren().addAll(producerLabel);
        }

        vbox.getChildren().addAll(nbScenesLabel);

        borderPane.setTop(vbox);

        // Classes

        titleLabel.getStyleClass().add("h2");
        idLabel.getStyleClass().addAll("p", "text-italic");
        nbScenesLabel.getStyleClass().addAll("p");
        directorLabel.getStyleClass().addAll("p");
        producerLabel.getStyleClass().addAll("p");

        titleLabel.setMaxWidth(Double.MAX_VALUE);
        idLabel.setMaxWidth(Double.MAX_VALUE);
        nbScenesLabel.setMaxWidth(Double.MAX_VALUE);
        directorLabel.setMaxWidth(Double.MAX_VALUE);
        producerLabel.setMaxWidth(Double.MAX_VALUE);

        titleLabel.setAlignment(Pos.CENTER);
        idLabel.setAlignment(Pos.CENTER);
        nbScenesLabel.setAlignment(Pos.CENTER);
        directorLabel.setAlignment(Pos.CENTER);
        producerLabel.setAlignment(Pos.CENTER);
    }

    private List<SceneRow> getSceneRowList ()
    {
        List<SceneRow> list = new ArrayList<>();
        movie.getScenes().forEach(scene -> list.add(new SceneRow(scene)));
        return list;
    }

    @Override
    protected void display ()
    {
        scenesTable.getItems().addAll(getSceneRowList());
        this.addComponents(borderPane);
    }

    private class SceneRow extends Model<Integer>
    {
        private final Scene scene;

        private SceneRow (Scene scene)
        {
            this.scene = scene;
        }

        private Scene getScene ()
        {
            return scene;
        }

        // Rows

        @Override
        public Integer getId ()
        {
            return scene.getId();
        }

        private String getPlaceName ()
        {
            Place place = scene.getPlace();
            return place == null ? "" : place.getName();
        }

        private boolean getOutside ()
        {
            return scene.getPlace() instanceof ExternalPlace;
        }

        private String getDayTime ()
        {
            DayTime dayTime = scene.getDayTime();
            return dayTime == null ? "" : dayTime.toString();
        }

        private int getNbSetups ()
        {
            return scene.getSetups().size();
        }
    }
}
