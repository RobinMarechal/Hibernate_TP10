package app.views.places;

import app.models.Place;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.mvc.Controller;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class AllPlacesView extends View
{
    private final List<Place> places;

    /** JFX components */
    private TableView<Place> placesTable = new TableView<>();
    private LinkerTableColumn<Place, Integer, Integer> idColumn;
    private LinkerTableColumn<Place, String, Integer> nameColumn;
    private LinkerTableColumn<Place, String, Integer> addressColumn;
    private TableColumn<Place, List<Scene>> nbScenesColumn;

    public AllPlacesView (Controller controller, List<Place> places)
    {
        super(controller);
        this.places = places;

        idColumn = new LinkerTableColumn<>(controller);
        nameColumn = new LinkerTableColumn<>(controller);
        addressColumn = new LinkerTableColumn<>(controller);
        nbScenesColumn = new TableColumn<>();

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setUpColumnFactories();
        setUpColumnTitles();
        setUpColumnDimensions();

        fitComponentToParent(placesTable);
        placesTable.getColumns().addAll(idColumn, nameColumn, addressColumn, nbScenesColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nbScenesColumn.setCellValueFactory(new PropertyValueFactory<>("scenes"));

        nbScenesColumn.setCellFactory(param -> new TableCell<Place, List<Scene>>()
        {
            @Override
            protected void updateItem (List<Scene> item, boolean empty)
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
        addressColumn.prepareEventHandler();
    }

    public void setUpColumnTitles ()
    {
        idColumn.setText("ID");
        nameColumn.setText("Name");
        addressColumn.setText("Address");
        nbScenesColumn.setText("Scenes");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
        nameColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        addressColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        nbScenesColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.29);
    }

    @Override
    protected void display ()
    {
        placesTable.getItems().addAll(places);

        this.getChildren().add(placesTable);
    }
}
