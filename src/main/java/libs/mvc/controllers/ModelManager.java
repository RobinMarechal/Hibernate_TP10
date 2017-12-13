package libs.mvc.controllers;

import fr.polytech.marechal.FormMap;
import libs.mvc.Model;

public interface ModelManager<MType extends Model>
{

    /**
     * Show the dialog for the creation of the model
     */
    default void showCreationDialog ()
    {
        showCreationDialog(null);
    }

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    void showCreationDialog (MType model);

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    void showDeleteDialog (MType model);

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    void create (FormMap form);

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    void delete (MType model);

    /**
     * Update a model and persists the changes to in the database
     *  @param model the model to update
     * @param form  the form with the model's data
     */
    void update (MType model, FormMap form);
}
