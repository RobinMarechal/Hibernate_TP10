package app.models.dao;

import app.models.Theatre;
import libs.mvc.models.ModelDAO;

/**
 * DAO class for the Theatre
 */
public class TheatreDAO extends ModelDAO<Theatre, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Theatre> getModelClazz ()
    {
        return Theatre.class;
    }
}
