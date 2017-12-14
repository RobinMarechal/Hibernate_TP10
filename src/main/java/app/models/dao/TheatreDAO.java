package app.models.dao;

import app.models.Theatre;
import libs.mvc.models.ModelDAO;

public class TheatreDAO extends ModelDAO<Theatre, Integer>
{
    @Override
    public Class<Theatre> getModelClazz ()
    {
        return Theatre.class;
    }
}
