package app.views.films;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import libs.mvc.Controller;
import libs.mvc.View;
import libs.ui.template.Template;

import java.util.List;

public class AllMoviesView extends View
{
    private List<Movie> movies;

    /** JFX components */
    private TableView<Movie> moviesTable = new TableView<>();
    private TableColumn<Movie, Number> idColumn = new TableColumn<>();
    private TableColumn<Movie, String> titleColumn = new TableColumn<>();
    private TableColumn<Movie, String> directorColumn = new TableColumn<>();
    private TableColumn<Movie, Producer> producerColumn = new TableColumn<>();

    public AllMoviesView (Controller controller, List<Movie> movies)
    {
        super(controller);
        this.movies = movies;

        setup();
        display();
    }

    @Override
    protected void setup ()
    {
        setUpColumnFactories();
        setUpColumnTitles();
        setUpColumnDimensions();

        fitComponentToParent(moviesTable);
        moviesTable.getColumns().addAll(idColumn, titleColumn, directorColumn, producerColumn);
    }

    public void setUpColumnFactories ()
    {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        directorColumn.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
        producerColumn.setCellValueFactory(cellData -> cellData.getValue().producersProperty());

//        producerColumn.setCellFactory(param -> new TableCell<Movie, ObjectProperty<Producer>>()
//        {
//            @Override
//            protected void updateItem (ObjectProperty<Producer> item, boolean empty)
//            {
//                super.updateItem(item, empty);
//
//                if (item != null && !empty) {
//                    textProperty().bind(item.get().nameProperty());
//                }
//                else {
//                    textProperty().unbind();
//                    setText("");
//                }
//            }
//        });
    }

    public void setUpColumnTitles ()
    {
        idColumn.setText("ID");
        titleColumn.setText("Title");
        directorColumn.setText("Director");
        producerColumn.setText("Producer");
    }

    public void setUpColumnDimensions ()
    {
        idColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.10);
        titleColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        directorColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.30);
        producerColumn.setPrefWidth(Template.CONTENT_WIDTH * 0.29);
    }

    @Override
    public void display ()
    {
        moviesTable.getItems().addAll(movies);
        this.getChildren().add(moviesTable);
    }
}
