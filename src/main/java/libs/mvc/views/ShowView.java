package libs.mvc.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import libs.mvc.controllers.Controller;
import libs.mvc.models.Model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ShowView<MType extends Model, CType extends Controller> extends View<CType>
{
    protected final static int BUTTON_PREF_WIDTH = 150;

    protected MType model;
    protected BorderPane borderPane;

    protected HBox topHbox;

    protected VBox topRightVbox;
    protected Button deleteBtn;
    protected Button updateBtn;
    protected Button showDetailsBtn;

    protected HashMap<Button, Boolean> mapBtn;

    protected VBox topLeftVBox;
    protected BorderPane topLeftBorderPane;

    public ShowView (CType controller, MType model)
    {
        super(controller);
        this.model = model;

        borderPane = new BorderPane();

        topLeftVBox = new VBox();
        topLeftBorderPane = new BorderPane();
        topLeftBorderPane.setTop(topLeftVBox);

        topRightVbox = new VBox(10);
        deleteBtn = new Button("Delete");
        updateBtn = new Button("Update");
        showDetailsBtn = new Button("More details");

        mapBtn = new HashMap<>();
        mapBtn.put(showDetailsBtn, true);
        mapBtn.put(updateBtn, true);
        mapBtn.put(deleteBtn, true);

        topHbox = new HBox(topLeftBorderPane, topRightVbox);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding(new Insets(0, 0, 5, 0));

        setAnchorOfComponent(borderPane, 5, 5, 5, 5);

        topHbox.setAlignment(Pos.CENTER);
        setAnchorOfComponent(topHbox, 0, 0, 0, 0);
        borderPane.setTop(topHbox);

        borderPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            topHbox.setMaxWidth(newValue.doubleValue());
            topLeftVBox.setPrefWidth(newValue.intValue() / 2);
            topLeftBorderPane.setPrefWidth(newValue.intValue() / 2);
            topRightVbox.setPrefWidth(newValue.intValue() / 2);
        });

        this.addComponents(borderPane);

        setupButtons();
        prepareEventHandlers();
    }

    @Override
    protected void setSizeOfColumnInTable (TableColumn column, Region container, double percentageWidth)
    {
        setSizeOfColumnInTable(column, container, percentageWidth, 10);
    }

    public void removeDetailsButton ()
    {
        mapBtn.replace(showDetailsBtn, false);
        topRightVbox.getChildren().remove(showDetailsBtn);
    }

    private void prepareEventHandlers ()
    {
        deleteBtn.setOnAction(event -> controller.showDeleteDialog(model));
        updateBtn.setOnAction(event -> controller.showUpdateDialog(model));
        showDetailsBtn.setOnAction(event -> controller.showDetails(model));
    }

    protected void setTopLeftComponents (Region bottom, Region... tops)
    {
        ObservableList<Node> children = topLeftVBox.getChildren();

        topLeftBorderPane.setBottom(null);
        children.clear();

        if (bottom != null) {
            topLeftBorderPane.setBottom(bottom);
        }

        if (tops != null && tops.length > 0) {
            children.addAll(tops);
        }
    }

    protected void addButton (Button button)
    {
        mapBtn.put(button, true);
        ObservableList<Node> children = topRightVbox.getChildren();

        children.clear();

        List<Button> activeBtns = mapBtn.entrySet().stream().filter(entry -> entry.getValue()).map(entry -> entry.getKey()).collect(Collectors.toList());
        long         nbActives  = activeBtns.size();

        if (nbActives == 4) {
            HBox hbox = new HBox(10);

            ObservableList<Node> hboxChildren = hbox.getChildren();
            hboxChildren.addAll(new VBox(10, activeBtns.get(0), activeBtns.get(1)), new VBox(10, activeBtns.get(2), activeBtns.get(3)));
            children.add(hbox);
        }
        else {
            children.addAll(activeBtns);
        }
    }

    public void setupButtons ()
    {
        ObservableList<Node> rightChildren = topRightVbox.getChildren();

        rightChildren.add(showDetailsBtn);
        rightChildren.add(updateBtn);
        rightChildren.add(deleteBtn);

        updateBtn.setPrefWidth(BUTTON_PREF_WIDTH);
        deleteBtn.setPrefWidth(BUTTON_PREF_WIDTH);
        showDetailsBtn.setPrefWidth(BUTTON_PREF_WIDTH);

        topRightVbox.setAlignment(Pos.CENTER);
        topRightVbox.setMaxHeight(Double.MAX_VALUE);

    }
}
