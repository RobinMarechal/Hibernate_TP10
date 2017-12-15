package app.models.dao;

import app.models.Setup;
import libs.mvc.models.ModelDAO;

/**
 * DAO class for the Setup
 */
public class SetupDAO extends ModelDAO<Setup, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Setup> getModelClazz ()
    {
        return Setup.class;
    }
}
