package app.views.scenes;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.SceneController;
import app.controllers.SetupController;
import app.models.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.views.ShowView;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class ShowSceneView extends ShowView<Scene, SceneController>
{
    private final MovieController movieController = new MovieController();
    private final SetupController setupController = new SetupController();
    private final PlaceController placeController = new PlaceController();

    private final Place place;
    private final List<Setup> setups;
    private final Movie movie;
    private final DayTime dayTime;

    private Label idLabel = new Label();
    private Label placeLabel = new Label();
    private Label dayTimeLabel = new Label();
    private Label descriptionLabel = new Label();
    private Label movieLabel = new Label();
    private Label setupsLabel = new Label();

    private TableView<Setup> setupsTable = new TableView<>();
    private LinkerTableColumn<Setup, Integer, Integer> setupIdColumn;
    private TableColumn<Setup, String> descriptionColumn;
    private TableColumn<Setup, List<Clap>> clapsColumn;

    public ShowSceneView (SceneController controller, Scene scene)
    {
        super(controller, scene);
        this.place = scene.getPlace();
        this.setups = scene.getSetups();
        this.movie = scene.getMovie();
        this.dayTime = scene.getDayTime();

        setupIdColumn = new LinkerTableColumn<>(setupController);
        clapsColumn = new TableColumn<>();
        descriptionColumn = new TableColumn<>();

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setupTopPart();
        setupBottomPart();
    }

    private void setupBottomPart ()
    {
        setupColumnHeaders();
        setupColumnFactories();
        setupColumnDimension();


        ObservableList<TableColumn<Setup, ?>> columns = setupsTable.getColumns();
        columns.addAll(setupIdColumn);
        columns.add(clapsColumn);
        columns.add(descriptionColumn);

        borderPane.setCenter(setupsTable);
    }

    private void setupColumnHeaders ()
    {
        setupIdColumn.setText("Setup ID");
        descriptionColumn.setText("Description");
        clapsColumn.setText("Claps");
    }

    private void setupColumnFactories ()
    {
        setupIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
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
                    setText(item);
                }
            }
        });

        setupIdColumn.prepareEventHandler();
    }

    private void setupColumnDimension ()
    {
        setSizeOfColumnInTable(setupIdColumn, setupsTable, 15);
        setSizeOfColumnInTable(clapsColumn, setupsTable, 15);
        setSizeOfColumnInTable(descriptionColumn, setupsTable, 70);
    }

    private void setupTopPart ()
    {
        boolean dayTimeEnabled = dayTime != null && dayTime != DayTime.NULL && place.getType() == PlaceType.EXTERNAL_PLACE;

        idLabel.setText("Scene N° " + model.getId());
        movieLabel.setText("Movie: '" + movie + "'");
        descriptionLabel.setText(model.getDescription());
        if (dayTimeEnabled) {
            dayTimeLabel.setText("Type: " + dayTime.toString());
        }
        placeLabel.setText("Place: " + place);
        setupsLabel.setText("Setups: ");

        descriptionLabel.setMaxWidth(Template.CONTENT_WIDTH / 4);
        descriptionLabel.setTooltip(new Tooltip("Please click here to see the entire description"));

        descriptionLabel.setOnMouseClicked(event -> {
            Dialog dialog = new Dialog()
            {
                private TextArea textArea = new TextArea(model.getDescription());
                private AnchorPane pane = new AnchorPane();

                // Anonymous class constructor
                {
                    setTitle("Description of scene n°" + model.getId());
                    initStyle(StageStyle.UTILITY);
                    pane.getChildren().add(textArea);
                    textArea.setEditable(false);
                    textArea.getStyleClass().add("text-pane-textarea");
                    AnchorPane.setBottomAnchor(textArea, -3d);
                    AnchorPane.setTopAnchor(textArea, -3d);
                    AnchorPane.setLeftAnchor(textArea, -3d);
                    AnchorPane.setRightAnchor(textArea, -3d);
                    textArea.setPrefWidth(500);
                    setContent(pane);
                }
            };

            DialogsManager.instance.openDialog(dialog);
        });

        setTopLeftComponents(setupsLabel, null);

        ObservableList<Node> children = topLeftVBox.getChildren();
        children.add(idLabel);
        children.add(movieLabel);
        if (dayTimeEnabled) {
            children.add(dayTimeLabel);
        }
        if (place != null) {
            children.add(placeLabel);
        }
        children.add(descriptionLabel);
        children.add(setupsLabel);

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
        setupsTable.getItems().addAll(model.getSetups());
    }
}