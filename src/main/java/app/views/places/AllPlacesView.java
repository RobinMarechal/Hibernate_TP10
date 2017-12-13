package app.views.places;

import app.controllers.PlaceController;
import app.models.Place;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.PlaceType;
import libs.mvc.View;
import libs.ui.components.links.LinkerTableColumn;
import libs.ui.template.Template;

import java.util.List;

public class AllPlacesView extends View<PlaceController>
{
    private final List<Place> places;

    /** JFX components */
    private TableView<Place> placesTable = new TableView<>();
    private LinkerTableColumn<Place, Integer, Integer> idColumn;
    private LinkerTableColumn<Place, String, Integer> nameColumn;
    private TableColumn<Place, PlaceType> typeColumn;
    private TableColumn<Place, String> addressColumn;
    private TableColumn<Place, List<Scene>> nbScenesColumn;

    public AllPlacesView (PlaceController controller, List<Place> places)
    {
        super(controller);
        this.places = places;

        idColumn = new LinkerTableColumn<>(controller);
        nameColumn = new LinkerTableColumn<>(controller);
        typeColumn = new TableColumn<>();
        addressColumn = new TableColumn<>();
        nbScenesColumn = new TableColumn<>();

        setup();
        display();
    }

    @SuppressWarnings ("Duplicates")
    @Override
    protected void setup ()
    {
        setUpColumnFactories();
        setUpColumnTitles();
        setUpColumnDimensions();

        fitComponentToParent(placesTable);
        placesTable.getColumns().addAll(idColumn, nameColumn, typeColumn, addressColumn, nbScenesColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nbScenesColumn.setCellValueFactory(new PropertyValueFactory<>("scenes"));

        nbScenesColumn.setCellFactory(param -> new TableCell<Place, List<Scene>>()
        {
            @Override
            protected void updateItem (List<Scene> item, boolean empty)
            {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(item.size() + "");
                }
            }
        });

        typeColumn.setCellFactory(param -> new TableCell<Place, PlaceType>(){

            @Override
            protected void updateItem (PlaceType item, boolean empty)
            {
                super.updateItem(item, empty);
                if(item != null && !empty)
                {
                    setText(item.toString());
                    this.setOnMouseClicked(event -> controller.showAllOfType(item));
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
        typeColumn.setText("Type");
        addressColumn.setText("Address");
        nbScenesColumn.setText("Scenes");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
        nameColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.22);
        typeColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.22);
        addressColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.36);
        nbScenesColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
    }

    @Override
    protected void display ()
    {
        placesTable.getItems().addAll(places);

        this.getChildren().add(placesTable);
    }
}
