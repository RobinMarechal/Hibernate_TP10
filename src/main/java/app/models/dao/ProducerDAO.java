package app.models.dao;

import app.models.Producer;
import libs.mvc.models.ModelDAO;

public class ProducerDAO extends ModelDAO<Producer, Integer>
{
    @Override
    public Class<Producer> getModelClazz ()
    {
        return Producer.class;
    }
}
