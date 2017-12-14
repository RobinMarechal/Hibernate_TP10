package app.views.movies;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.ProducerController;
import app.controllers.SceneController;
import app.models.Movie;
import app.models.Place;
import app.models.Producer;
import app.models.Scene;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.models.Model;
import libs.mvc.views.ShowView;
import libs.ui.components.links.LinkerTableColumn;

import java.util.ArrayList;
import java.util.List;

public class ShowMovieView extends ShowView<Movie, MovieController>
{
    private final Producer producer;

    private final SceneController sceneController;
    private final PlaceController placeController;
    private final List<Scene> scenes;

    private Label titleLabel;
    private Label idLabel;
    private Label nbScenesLabel;
    private Label directorLabel;
    private Label producerLabel; // Handle Click

    private TableView<SceneRow> scenesTable;
    private LinkerTableColumn<SceneRow, Integer, Integer> idColumn;
    private LinkerTableColumn<SceneRow, String, Integer> placeNameColumn;
    private TableColumn<SceneRow, PlaceType> placeTypeColumn; // Handle click event
    private TableColumn<SceneRow, DayTime> dayTimeColumn; // Handle click Event
    private TableColumn<SceneRow, Integer> nbSetupsColumn;

    public ShowMovieView (MovieController controller, Movie movie, List<Scene> scenes)
    {
        super(controller, movie);
        this.producer = movie.getProducer();
        this.scenes = scenes;

        sceneController = new SceneController();
        placeController = new PlaceController();

        titleLabel = new Label();
        idLabel = new Label();
        nbScenesLabel = new Label();
        directorLabel = new Label();
        producerLabel = new Label();

        scenesTable = new TableView<>();
        idColumn = new LinkerTableColumn<>(sceneController);
        placeNameColumn = new LinkerTableColumn<>(placeController);
        nbSetupsColumn = new TableColumn<>();
        placeTypeColumn = new TableColumn<>();
        dayTimeColumn = new TableColumn<>();

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setupTopPart();
        setupBottomPart();
        prepareEventHandlers();
    }

    public void prepareEventHandlers ()
    {
        if (producer != null) {
            producerLabel.getStyleClass().add("link");
            producerLabel.setOnMouseClicked(event -> new ProducerController().show(producer.getId()));
        }
    }

    private void setupBottomPart ()
    {
        setupColumnHeaders();
        setupColumnFactories();
        setupColumnDimension();

        idColumn.prepareEventHandler();
        placeNameColumn.prepareEventHandler();

        scenesTable.getColumns().addAll(idColumn, nbSetupsColumn, placeTypeColumn, dayTimeColumn, placeNameColumn);
        borderPane.setCenter(scenesTable);
    }

    private void setupColumnHeaders ()
    {
        idColumn.setText("Scene ID");
        nbSetupsColumn.setText("Setups");
        placeTypeColumn.setText("Place Type");
        dayTimeColumn.setText("Time of Day");
        placeNameColumn.setText("Place");
    }

    private void setupColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nbSetupsColumn.setCellValueFactory(new PropertyValueFactory<>("nbSetups"));
        placeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("placeType"));
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dayTime"));
        placeNameColumn.setCellValueFactory(new PropertyValueFactory<>("placeName"));

        placeTypeColumn.setCellFactory(event -> new TableCell<SceneRow, PlaceType>()
        {
            @Override
            protected void updateItem (PlaceType item, boolean empty)
            {
                super.updateItem(item, empty);
                ObservableList<String> styleClass = getStyleClass();
                styleClass.remove("link");

                if (item != null && !empty) {
                    styleClass.add("link");
                    setText(item.toString());
                    setOnMouseClicked(ev -> controller.showScenesOfMovieAtPlaceType(model, item));
                }
            }
        });

        dayTimeColumn.setCellFactory(event -> new TableCell<SceneRow, DayTime>()
        {
            @Override
            protected void updateItem (DayTime item, boolean empty)
            {
                super.updateItem(item, empty);
                ObservableList<String> styleClass = getStyleClass();
                styleClass.remove("link");

                if (item != null && !empty) {
                    styleClass.add("link");
                    setText(item.toString());
                    setOnMouseClicked(ev -> controller.showScenesOfMovieAtDayTime(model, item));
                }
            }
        });
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(idColumn, scenesTable, 10);
        setSizeOfColumnInTable(nbSetupsColumn, scenesTable, 10);
        setSizeOfColumnInTable(placeTypeColumn, scenesTable, 22);
        setSizeOfColumnInTable(dayTimeColumn, scenesTable, 15);
        setSizeOfColumnInTable(placeNameColumn, scenesTable, 43);
    }

    public void setupLabels ()
    {
    }

    private void setupTopPart ()
    {
        titleLabel.setText(model.getTitle());
        idLabel.setText("ID: " + model.getId());
        nbScenesLabel.setText("Scenes:");
        directorLabel.setText("Director: " + model.getDirector());
        producerLabel.setText("Produced by: " + (this.producer == null ? "null" : this.producer.getName()));

        setTopLeftComponents(nbScenesLabel, titleLabel, directorLabel, producerLabel);

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

        topLeftVBox.setAlignment(Pos.CENTER);
        topLeftVBox.setMaxHeight(Double.MAX_VALUE);
    }

    private List<SceneRow> getSceneRowList ()
    {
        List<SceneRow> list = new ArrayList<>();
        scenes.forEach(scene -> list.add(new SceneRow(scene)));
        return list;
    }

    @Override
    protected void display ()
    {
        scenesTable.getItems().addAll(getSceneRowList());
    }


    // ---------------------------------
    // INNER CLASS
    // ---------------------------------

    public class SceneRow extends Model<Integer>
    {
        private final Scene scene;

        public SceneRow (Scene scene)
        {
            this.scene = scene;
        }

        public Scene getScene ()
        {
            return scene;
        }

        // Rows

        @Override
        public Integer getId ()
        {
            return scene.getId();
        }

        public String getPlaceName ()
        {
            Place place = scene.getPlace();
            return place == null ? "" : place.getName();
        }

        public PlaceType getPlaceType ()
        {
            return scene.getPlace().getType();
        }

        public DayTime getDayTime ()
        {
            return scene.getDayTime();
        }

        public int getNbSetups ()
        {
            return scene.getSetups().size();
        }
    }
}
