package app.models.dao;

import app.models.Producer;
import libs.mvc.models.ModelDAO;

/**
 * The DAO class for the Producers
 */
public class ProducerDAO extends ModelDAO<Producer, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Producer> getModelClazz ()
    {
        return Producer.class;
    }
}
