package app.views.scenes;

import app.controllers.SceneController;
import app.models.Movie;
import app.models.Place;
import app.models.Scene;
import fr.polytech.marechal.FieldValueType;
import fr.polytech.marechal.FormMap;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import libs.DayTime;
import libs.PlaceType;
import libs.ui.components.dialogs.Dialog;

import java.util.ArrayList;
import java.util.List;

public class CreateSceneView extends Dialog
{
    private final SceneController controller;
    private final List<Place> places;
    private final Movie movie;
    private final Scene scene;
    private final ArrayList<PlaceItem> placeItems;

    ObservableList<Node> formBoxChildren;

    private FormMap form;

    private Label dialogTitle;
    private VBox formBox;

    private Label descriptionLabel;
    private TextArea descriptionField;
    private VBox descriptionFormGroup;

    private ComboBox<Movie> movieField;

    private Label placeLabel;
    private ComboBox<PlaceItem> placeField;
    private VBox placeFormGroup;

    private Label dayTimeLabel;
    private ComboBox<DayTime> dayTimeField;
    private VBox dayTimeFormGroup;
    private boolean shown = false;

    private Button submit;
    private VBox submitFormGroup;
    private Place place;

    public CreateSceneView (SceneController controller, List<Place> places, Movie movie, Scene scene)
    {
        super();
        this.controller = controller;
        this.places = places;
        this.placeItems = new ArrayList<>();
        this.movie = movie;
        this.scene = scene;

        for (Place place : places) {
            placeItems.add(new PlaceItem(place));
        }

        this.sizeToScene();

        formBox = new VBox(20);
        formBox.getStyleClass().add("form");
        formBox.setPadding(new Insets(20, 40, 20, 40));
        addStylesheetsTo(formBox);

        dialogTitle = new Label("Create a new Scene for the movie '" + movie.getTitle() + "'");
        dialogTitle.getStyleClass().add("h2");
        dialogTitle.setMaxWidth(Double.MAX_VALUE);
        dialogTitle.setAlignment(Pos.CENTER);

        placeLabel = new Label("Place: ");
        placeField = new ComboBox<>();
        placeField.setPromptText("Select a place...");
        placeField.setMaxWidth(Double.MAX_VALUE);
        placeField.getItems().addAll(placeItems);
        placeFormGroup = new VBox(placeLabel, placeField);
        //        placeFormGroup.setDisable(true);

        dayTimeLabel = new Label("Day Time: ");
        dayTimeField = new ComboBox<>();
        dayTimeField.setPromptText("Select a Day Time...");
        dayTimeField.setMaxWidth(Double.MAX_VALUE);
        dayTimeField.getItems().addAll(DayTime.DAY, DayTime.NIGHT);
        dayTimeField.getSelectionModel().select(0);
        dayTimeFormGroup = new VBox(dayTimeLabel, dayTimeField);
        dayTimeFormGroup.setVisible(false);
        dayTimeFormGroup.setManaged(false);

        descriptionLabel = new Label("Description: ");
        descriptionField = new TextArea();
        descriptionField.setPrefRowCount(5);
        descriptionFormGroup = new VBox();
        descriptionFormGroup.getChildren().addAll(descriptionLabel, descriptionField);
        descriptionField.setPrefHeight(100);


        movieField = new ComboBox<>();
        movieField.getItems().add(movie);
        movieField.getSelectionModel().select(0);

        submit = new Button("Submit");
        submitFormGroup = new VBox();
        submitFormGroup.setAlignment(Pos.CENTER);
        submitFormGroup.getChildren().add(submit);
        submitFormGroup.getStyleClass().add("submit-form-group");

        formBoxChildren = formBox.getChildren();
        formBoxChildren.addAll(dialogTitle, placeFormGroup, dayTimeFormGroup, descriptionFormGroup, submitFormGroup);

        setup();

        setContent(formBox);
    }

    public void update ()
    {
        setContent(formBox);
    }

    public void setup ()
    {
        placeField.valueProperty().addListener((observable, oldValue, newValue) -> {
            PlaceType type = newValue.getType();
            if (type == PlaceType.EXTERNAL_PLACE) {
                dayTimeFormGroup.setVisible(true);
                dayTimeFormGroup.setManaged(true);
            }
            else if (type == PlaceType.THEATRE) {
                dayTimeFormGroup.setVisible(false);
                dayTimeFormGroup.setManaged(false);
            }
        });
        form = new FormMap();

        if (scene != null) {
            dayTimeField.getSelectionModel().select(scene.getDayTime());
            placeField.getSelectionModel().select(places.indexOf(scene.getPlace()));
            descriptionField.setText(scene.getDescription());
        }

        form.add("dayTime", FieldValueType.UNDEFINED, dayTimeField, true);
        form.add("place", FieldValueType.UNDEFINED, placeField, true);
        form.add("description", FieldValueType.VARCHAR, descriptionField);
        form.add("movie", FieldValueType.UNDEFINED, movieField);
        form.setSubmitButton(submit);

        form.setOnSubmit(event -> controller.update(scene, form));
    }

    public class PlaceItem
    {
        private final Place place;
        private final String name;
        private final PlaceType type;

        public PlaceItem (Place place)
        {
            this.place = place;
            this.name = place.getName();
            this.type = place.getType();
        }

        public PlaceType getType ()
        {
            return type;
        }

        @Override
        public String toString ()
        {
            return name + " (" + type + ")";
        }

        public Place getPlace ()
        {
            return place;
        }
    }
}
