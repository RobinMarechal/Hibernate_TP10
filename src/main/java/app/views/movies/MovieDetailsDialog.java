package app.views.movies;

import app.controllers.MovieController;
import app.models.Movie;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import libs.ui.components.dialogs.Dialog;

import java.util.HashMap;

public class MovieDetailsDialog extends Dialog
{
    private long nbScenes;
    private long nbSetups;
    private long nbClaps;
    private long nbTheatres;
    private long nbOutside;
    private long totalClapDuration;
    private long nbScenesByDay;
    private long nbScenesByNight;

    private VBox container;

    private VBox scenesBox;
    private Label scenesTitle;
    private Label scenesInTheatre;
    private Label scenesOutside;
    private Label scenesOutsideByDay;
    private Label scenesOutsideByNight;
    private Label totalScenes;

    private VBox setupsBox;
    private Label setupsTitle;
    private Label nbSetupsLabel;
    private Label avgSetupsPerScene;

    private VBox clapsBox;
    private Label clapsTitle;
    private Label nbClapsLabel;
    private Label avgClapPerSetup;
    private Label avgClapPerScene;
    private Label totalClapDurationLabel;
    private Label avgClapDuration;

    public MovieDetailsDialog (MovieController movieController, Movie movie, HashMap<String, Long> details)
    {
        this.nbScenes = details.get("nbScenes");
        this.nbTheatres = details.get("nbTheatres");
        this.nbOutside = details.get("nbOutside");
        this.nbSetups = details.get("nbSetups");
        this.nbClaps = details.get("nbClaps");
        this.totalClapDuration = details.get("totalClapDuration");
        this.nbScenesByDay = details.get("nbScenesByDay");
        this.nbScenesByNight = details.get("nbScenesByNight");

        prepareScenesBox();
        prepareSetupsBox();
        prepareClapsBox();

        container = new VBox(10, scenesBox, setupsBox, clapsBox);
        container.setPadding(new Insets(20, 75, 50, 75));
        container.setAlignment(Pos.CENTER);
        container.getStyleClass().add("bg-almost-white");
        addStylesheetTo(container);

        setTitle("Details of '" + movie.getTitle() + "'");

        setContent(container);
    }

    private void setPCssStyle (Label... labels)
    {
        for (Label label : labels) {
            label.getStyleClass().add("p-sm");
        }
    }

    private void prepareScenesBox ()
    {
        scenesTitle = new Label("Scenes: ");
        scenesTitle.getStyleClass().add("h4");

        totalScenes = new Label("\tNumber: " + nbScenes);
        scenesInTheatre = new Label("\tIn Theatre: " + nbTheatres);
        scenesOutside = new Label("\tOutside: " + nbOutside);
        scenesOutsideByDay = new Label("\t\tBy Day: " + nbScenesByDay);
        scenesOutsideByNight = new Label("\t\tBy Night: " + nbScenesByNight);

        setPCssStyle(totalScenes, scenesInTheatre, scenesOutside, scenesOutsideByDay, scenesOutsideByNight);

        scenesBox = new VBox();
        scenesBox.setAlignment(Pos.BASELINE_LEFT);

        ObservableList<Node> children = scenesBox.getChildren();
        children.add(scenesTitle);
        children.add(totalScenes);
        children.add(scenesInTheatre);
        children.add(scenesOutside);
        children.add(scenesOutsideByDay);
        children.add(scenesOutsideByNight);
    }

    private void prepareSetupsBox ()
    {
        setupsTitle = new Label("Setups:");
        setupsTitle.getStyleClass().add("h4");

        nbSetupsLabel = new Label("\tTotal: " + nbSetups);

        avgSetupsPerScene = new Label("\tSetups Per Scene average: " + String.format("%.2f", (double) nbSetups / nbScenes));

        setPCssStyle(nbSetupsLabel, avgSetupsPerScene);

        setupsBox = new VBox();
        setupsBox.setAlignment(Pos.BASELINE_LEFT);

        ObservableList<Node> children = setupsBox.getChildren();
        children.add(setupsTitle);
        children.add(nbSetupsLabel);
        children.add(avgSetupsPerScene);
    }

    private void prepareClapsBox ()
    {
        clapsTitle = new Label("Claps:");
        clapsTitle.getStyleClass().add("h4");

        nbClapsLabel = new Label("\tTotal claps: " + nbClaps);
        avgClapPerSetup = new Label("\tClaps per Setup average: " + String.format("%.2f", (double) nbClaps / nbSetups));
        avgClapPerScene = new Label("\tClaps per Scene average: " + String.format("%.2f", (double) nbClaps / nbScenes));
        totalClapDurationLabel = new Label("\tTotal claps duration: " + totalClapDuration);
        avgClapDuration = new Label("\tClap duration average: " + String.format("%.2f", (double) totalClapDuration / nbClaps));

        setPCssStyle(nbClapsLabel, avgClapPerSetup, avgClapPerScene, totalClapDurationLabel, avgClapDuration);

        clapsBox = new VBox();
        clapsBox.setAlignment(Pos.BASELINE_LEFT);

        ObservableList<Node> children = clapsBox.getChildren();
        children.add(clapsTitle);
        children.add(nbClapsLabel);
        children.add(avgClapPerSetup);
        children.add(avgClapPerScene);
        children.add(totalClapDurationLabel);
        children.add(avgClapDuration);
    }
}
