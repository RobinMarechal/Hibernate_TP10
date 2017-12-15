package app.views.scenes;

import app.controllers.SceneController;
import app.models.Movie;
import app.models.Scene;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import libs.ui.components.dialogs.Dialog;

import java.util.HashMap;

public class SceneDetailsDialog extends Dialog
{
    private final Movie movie;
    private long nbSetups;
    private long nbClaps;
    private long totalClapDuration;

    private VBox container;

    private VBox setupsBox;
    private Label setupsTitle;
    private Label nbSetupsLabel;

    private VBox clapsBox;
    private Label clapsTitle;
    private Label nbClapsLabel;
    private Label avgClapPerSetup;
    private Label totalClapDurationLabel;
    private Label avgClapDuration;

    public SceneDetailsDialog (SceneController sceneController, Scene scene, HashMap<String, Long> details)
    {
        this.nbSetups = details.get("nbSetups");
        this.nbClaps = details.get("nbClaps");
        this.totalClapDuration = details.get("totalClapDuration");
        this.movie = scene.getMovie();

        prepareSetupsBox();
        prepareClapsBox();

        container = new VBox(10, setupsBox, clapsBox);
        container.setPadding(new Insets(20, 75, 50, 75));
        container.setAlignment(Pos.CENTER);
        container.getStyleClass().add("bg-almost-white");
        addStylesheetsTo(container);

        setTitle("Details of the scene n°" + scene.getId() + (this.movie == null ? "" : "of the movie '" + this.movie.getTitle() + "'"));

        setContent(container);
    }

    private void setPCssStyle (Label... labels)
    {
        for (Label label : labels) {
            label.getStyleClass().add("p-sm");
        }
    }


    private void prepareSetupsBox ()
    {
        setupsTitle = new Label("Setups:");
        setupsTitle.getStyleClass().add("h4");

        nbSetupsLabel = new Label("\tTotal: " + nbSetups);

        setPCssStyle(nbSetupsLabel);

        setupsBox = new VBox();
        setupsBox.setAlignment(Pos.BASELINE_LEFT);

        ObservableList<Node> children = setupsBox.getChildren();
        children.add(setupsTitle);
        children.add(nbSetupsLabel);
    }

    private void prepareClapsBox ()
    {
        clapsTitle = new Label("Claps:");
        clapsTitle.getStyleClass().add("h4");

        nbClapsLabel = new Label("\tTotal claps: " + nbClaps);
        avgClapPerSetup = new Label("\tClaps per Setup average: " + String.format("%.2f", (double) nbClaps / nbSetups));
        totalClapDurationLabel = new Label("\tTotal claps duration: " + totalClapDuration);
        avgClapDuration = new Label("\tClap duration average: " + String.format("%.2f", (double) totalClapDuration / nbClaps));

        setPCssStyle(nbClapsLabel, avgClapPerSetup, totalClapDurationLabel, avgClapDuration);

        clapsBox = new VBox();
        clapsBox.setAlignment(Pos.BASELINE_LEFT);

        ObservableList<Node> children = clapsBox.getChildren();
        children.add(clapsTitle);
        children.add(nbClapsLabel);
        children.add(avgClapPerSetup);
        children.add(totalClapDurationLabel);
        children.add(avgClapDuration);
    }
}
