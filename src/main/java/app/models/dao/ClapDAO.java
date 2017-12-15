package app.models.dao;

import app.models.Clap;
import app.models.pk.ClapPrimaryKey;
import libs.mvc.models.ModelDAO;

/**
 * the DAO class for the Claps
 */
public class ClapDAO extends ModelDAO<Clap, ClapPrimaryKey>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Clap> getModelClazz ()
    {
        return Clap.class;
    }
}
