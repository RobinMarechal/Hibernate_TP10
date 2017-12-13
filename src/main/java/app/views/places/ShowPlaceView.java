package app.views.places;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.models.Movie;
import app.models.Place;
import app.models.Scene;
import app.models.Setup;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import libs.DayTime;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;

import java.util.List;

public class ShowPlaceView extends View<PlaceController>
{
    private final Place place;

    private final MovieController movieController = new MovieController();

    private BorderPane borderPane = new BorderPane();

    private VBox vbox = new VBox();
    private Label nameLabel = new Label();
    private Label idLabel = new Label();
    private Label typeLabel = new Label();
    private Label addressLabel = new Label();
    private Label nbScenesLabel = new Label();

    private TableView<Scene> scenesTable = new TableView<>();
    private LinkerTableColumn<Scene, Integer, Integer> idColumn;
    private LinkerTableColumn<Scene, Movie, Integer> movieColumn;
    private TableColumn<Scene, DayTime> dayTimeColumn;
    private TableColumn<Scene, List<Setup>> setupsColumn;

    public ShowPlaceView (PlaceController controller, Place place)
    {
        super(controller);
        this.place = place;

        idColumn = new LinkerTableColumn<>(movieController);
        movieColumn = new LinkerTableColumn<>(movieController);
        setupsColumn = new TableColumn<>();
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
    }

    private void setupBottomPart ()
    {
        setupColumnHeaders();
        setupColumnFactories();
        setupColumnDimension();


        ObservableList<TableColumn<Scene, ?>> columns = scenesTable.getColumns();
        columns.addAll(idColumn);
        columns.add(movieColumn);
        columns.add(dayTimeColumn);
        columns.add(setupsColumn);
        borderPane.setCenter(scenesTable);
    }

    private void setupColumnHeaders ()
    {
        idColumn.setText("ID");
        movieColumn.setText("Movie");
        dayTimeColumn.setText("Day Time");
        setupsColumn.setText("Setups");
    }

    private void setupColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("movie"));
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dayTime"));
        setupsColumn.setCellValueFactory(new PropertyValueFactory<>("setups"));

        movieColumn.setCellFactory(param -> new TableCell<Scene, Movie>()
        {
            @Override
            protected void updateItem (Movie item, boolean empty)
            {
                super.updateItem(item, empty);

                if (!empty && item != null) {
                    setText(item.getTitle());
                    setOnMouseClicked(event -> movieController.show(item.getId()));
                }
            }
        });

//        dayTimeColumn.setCellFactory(param -> new TableCell<Scene, DayTime>()
//        {
//            @Override
//            protected void updateItem (DayTime item, boolean empty)
//            {
//                super.updateItem(item, empty);
//                if (!empty && item != null) {
//                    setText(item.toString());
//                }
//            }
//        });

        setupsColumn.setCellFactory(event -> new TableCell<Scene, List<Setup>>()
        {
            @Override
            protected void updateItem (List<Setup> item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.size() + "");
                }
            }
        });

        idColumn.prepareEventHandler();
        movieColumn.prepareEventHandler();
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(idColumn, scenesTable, 15);
        setSizeOfColumnInTable(movieColumn, scenesTable, 50);
        setSizeOfColumnInTable(dayTimeColumn, scenesTable, 20);
        setSizeOfColumnInTable(setupsColumn, scenesTable, 15);
    }

    private void setupTopPart ()
    {
        nameLabel.setText(place.getName());
        addressLabel.setText(place.getAddress());
        idLabel.setText("ID: " + place.getId());
        typeLabel.setText("Type: " + place.getType().toString());
        nbScenesLabel.setText("Scenes (" + place.getScenes().size() + "): ");

        typeLabel.setOnMouseClicked(event -> controller.showAllOfType(place.getType()));

        ObservableList<Node> children = vbox.getChildren();
        children.add(nameLabel);
        //        children.add(idLabel);
        children.add(addressLabel);
        children.add(typeLabel);
        children.add(nbScenesLabel);

        borderPane.setTop(vbox);

        nameLabel.getStyleClass().add("h2");
        idLabel.getStyleClass().addAll("p", "text-italic");
        nbScenesLabel.getStyleClass().addAll("p");
        typeLabel.getStyleClass().add("p");
        addressLabel.getStyleClass().add("p");

        nameLabel.setMaxWidth(Double.MAX_VALUE);
        idLabel.setMaxWidth(Double.MAX_VALUE);
        nbScenesLabel.setMaxWidth(Double.MAX_VALUE);
        typeLabel.setMaxWidth(Double.MAX_VALUE);
        addressLabel.setMaxWidth(Double.MAX_VALUE);

        nameLabel.setAlignment(Pos.CENTER);
        idLabel.setAlignment(Pos.CENTER);
        nbScenesLabel.setAlignment(Pos.CENTER);
        typeLabel.setAlignment(Pos.CENTER);
        addressLabel.setAlignment(Pos.CENTER);
    }

    @Override
    protected void display ()
    {
        scenesTable.getItems().addAll(place.getScenes());
        this.addComponents(borderPane);
    }
}
