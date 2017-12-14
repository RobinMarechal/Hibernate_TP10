package app.models.dao;

import app.models.Scene;
import libs.mvc.models.ModelDAO;

public class SceneDAO extends ModelDAO<Scene, Integer>
{
    @Override
    public Class<Scene> getModelClazz ()
    {
        return Scene.class;
    }
}
