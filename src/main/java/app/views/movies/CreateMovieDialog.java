package app.views.movies;

import app.controllers.MovieController;
import app.models.Movie;
import app.models.Producer;
import fr.polytech.marechal.FieldValueType;
import fr.polytech.marechal.FormMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import libs.ui.components.dialogs.Dialog;

import java.util.List;

public class CreateMovieDialog extends Dialog
{
    private final MovieController controller;
    private final List<Producer> producers;
    private final Movie movie;
    private FormMap form;

    private Label dialogTitle;
    private VBox formBox;

    private Label titleLabel;
    private TextField titleField;
    private VBox titleFormGroup;

    private Label directorLabel;
    private TextField directorField;
    private VBox directorFormGroup;

    private Label producerLabel;
    private ComboBox<Producer> producerField;
    private VBox producerFormGroup;

    private Button submit;
    private VBox submitFormGroup;

    /**
     * Default constructor
     */
    public CreateMovieDialog (MovieController controller, Movie movie, List<Producer> producers)
    {
        super();
        this.movie = movie;
        this.controller = controller;
        this.producers = producers;

        this.sizeToScene();

        formBox = new VBox(20);
        formBox.getStyleClass().add("form");
        formBox.setPadding(new Insets(20, 40, 20, 40));
        addStylesheetTo(formBox);

        dialogTitle = new Label((movie == null ? "Creation of a movie" : "Update a movie"));
        dialogTitle.getStyleClass().add("h2");
        dialogTitle.setMaxWidth(Double.MAX_VALUE);
        dialogTitle.setAlignment(Pos.CENTER);

        titleLabel = new Label("Title: ");
        titleField = new TextField();
        titleFormGroup = new VBox();
        titleFormGroup.getChildren().addAll(titleLabel, titleField);

        directorLabel = new Label("Director: ");
        directorField = new TextField();
        directorFormGroup = new VBox();
        directorFormGroup.getChildren().addAll(directorLabel, directorField);

        producerLabel = new Label("Producer: ");
        producerField = new ComboBox<>();
        producerField.setMaxWidth(Double.MAX_VALUE);
        producerFormGroup = new VBox();
        producerField.setPromptText("Select a producer...");
        producerFormGroup.getChildren().addAll(producerLabel, producerField);

        submit = new Button("Submit");
        submitFormGroup = new VBox();
        submitFormGroup.setAlignment(Pos.CENTER);
        submitFormGroup.getChildren().add(submit);
        submitFormGroup.getStyleClass().add("submit-form-group");

        formBox.getChildren().addAll(dialogTitle, titleFormGroup, directorFormGroup, producerFormGroup, submitFormGroup);

        setup();

        setContent(formBox);
    }

    public void setup ()
    {
        form = new FormMap();

        if(movie != null)
        {
            titleField.setText(movie.getTitle());
            directorField.setText(movie.getDirector());
            producerField.getSelectionModel().select(movie.getProducer());
        }

        form.setSubmitButton(submit);
        form.add("title", FieldValueType.NAME, titleField, true);
        form.add("director", FieldValueType.NAME, directorField, true);
        form.add("producer", FieldValueType.UNDEFINED, producerField, true);

        form.setOnSubmit(event -> controller.update(movie, form));

        producerField.getItems().addAll(producers);
    }
}
