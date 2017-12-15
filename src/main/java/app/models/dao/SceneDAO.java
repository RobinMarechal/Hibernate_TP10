package app.models.dao;

import app.models.Scene;
import libs.mvc.models.ModelDAO;

/**
 * DAO class for the Scenes
 */
public class SceneDAO extends ModelDAO<Scene, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Scene> getModelClazz ()
    {
        return Scene.class;
    }
}
