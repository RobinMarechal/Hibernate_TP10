package app.views.scenes;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.SceneController;
import app.controllers.SetupController;
import app.models.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import libs.DayTime;
import libs.mvc.View;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class ShowSceneView extends View<SceneController>
{
    private final Scene scene;

    private final MovieController movieController = new MovieController();
    private final SetupController setupController = new SetupController();
    private final PlaceController placeController = new PlaceController();

    private final Place place;
    private final List<Setup> setups;
    private final Movie movie;
    private final DayTime dayTime;

    private BorderPane borderPane = new BorderPane();

    private VBox vbox = new VBox();

    private Label idLabel = new Label();
    private Label placeLabel = new Label();
    private Label dayTimeLabel = new Label();
    private Label descriptionLabel = new Label();
    private Label movieLabel = new Label();
    private Label setupsLabel = new Label();

    private TableView<Setup> setupsTable = new TableView<>();
    private LinkerTableColumn<Setup, Integer, Integer> idColumn;
    private TableColumn<Setup, String> descriptionColumn;
    private TableColumn<Setup, List<Clap>> clapsColumn;

    public ShowSceneView (SceneController controller, Scene scene)
    {
        super(controller);
        this.scene = scene;
        this.place = scene.getPlace();
        this.setups = scene.getSetups();
        this.movie = scene.getMovie();
        this.dayTime = scene.getDayTime();

        idColumn = new LinkerTableColumn<>(controller);
        clapsColumn = new TableColumn<>();
        descriptionColumn = new TableColumn<>();

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


        ObservableList<TableColumn<Setup, ?>> columns = setupsTable.getColumns();
        columns.addAll(idColumn);
        columns.add(descriptionColumn);
        columns.add(clapsColumn);
        borderPane.setCenter(setupsTable);
    }

    private void setupColumnHeaders ()
    {
        idColumn.setText("ID");
        descriptionColumn.setText("Description");
        clapsColumn.setText("Claps");
    }

    private void setupColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        clapsColumn.setCellValueFactory(new PropertyValueFactory<>("claps"));

        clapsColumn.setCellFactory(event -> new TableCell<Setup, List<Clap>>()
        {
            @Override
            protected void updateItem (List<Clap> item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.size() + "");
                }
            }
        });

        descriptionColumn.setCellFactory(event -> new TableCell<Setup, String>()
        {
            @Override
            protected void updateItem (String item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setTooltip(new Tooltip("Please click on the ID to see the details."));
                }
                else {
                    setTooltip(null);
                }
            }
        });

        idColumn.prepareEventHandler();
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(idColumn, setupsTable, 15);
        setSizeOfColumnInTable(clapsColumn, setupsTable, 15);
        setSizeOfColumnInTable(descriptionColumn, setupsTable, 70);
    }

    private void setupTopPart ()
    {
        idLabel.setText("Scene N° " + scene.getId());
        movieLabel.setText("" + movie);
        descriptionLabel.setText(scene.getDescription());
        dayTimeLabel.setText("Type: " + dayTime.toString());
        placeLabel.setText("Place: " + place);
        setupsLabel.setText("Setups (" + scene.getSetups().size() + "): ");

        descriptionLabel.setMaxWidth(Template.CONTENT_WIDTH / 2);
        descriptionLabel.setTooltip(new Tooltip("Please click here to see the entire description"));

        descriptionLabel.setOnMouseClicked(event -> {
            Dialog dialog = new Dialog() {

                private TextArea textArea = new TextArea(scene.getDescription());
                private Pane pane = new Pane();

                {
                    setContent(pane);
                    pane.setPadding(new Insets(20));
                    pane.getChildren().add(textArea);
                    textArea.setEditable(false);
                }
            };

            DialogsManager.instance.openDialog(dialog);
        });

//        dayTimeLabel.setOnMouseClicked(event -> controller.showAllOfDayTime(dayTime));

        ObservableList<Node> children = vbox.getChildren();
        children.add(idLabel);
        if (movie != null) {
            children.add(movieLabel);
        }
        children.add(dayTimeLabel);
        if (place != null) {
            children.add(placeLabel);
        }
        children.add(descriptionLabel);
        children.add(setupsLabel);

        borderPane.setTop(vbox);

        idLabel.getStyleClass().add("h2");
        movieLabel.getStyleClass().addAll("p", "text-italic");
        descriptionLabel.getStyleClass().addAll("p");
        dayTimeLabel.getStyleClass().add("p");
        placeLabel.getStyleClass().add("p");
        setupsLabel.getStyleClass().add("p");

        idLabel.setMaxWidth(Double.MAX_VALUE);
        movieLabel.setMaxWidth(Double.MAX_VALUE);
        descriptionLabel.setMaxWidth(Double.MAX_VALUE);
        dayTimeLabel.setMaxWidth(Double.MAX_VALUE);
        placeLabel.setMaxWidth(Double.MAX_VALUE);
        setupsLabel.setMaxWidth(Double.MAX_VALUE);

        idLabel.setAlignment(Pos.CENTER);
        movieLabel.setAlignment(Pos.CENTER);
        descriptionLabel.setAlignment(Pos.CENTER);
        dayTimeLabel.setAlignment(Pos.CENTER);
        placeLabel.setAlignment(Pos.CENTER);
        setupsLabel.setAlignment(Pos.CENTER);
    }

    @Override
    protected void display ()
    {
        setupsTable.getItems().addAll(scene.getSetups());
        this.addComponents(borderPane);
    }
}