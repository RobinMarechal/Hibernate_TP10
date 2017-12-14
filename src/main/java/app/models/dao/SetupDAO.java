package app.models.dao;

import app.models.Setup;
import libs.mvc.models.ModelDAO;

public class SetupDAO extends ModelDAO<Setup, Integer>
{
    @Override
    public Class<Setup> getModelClazz ()
    {
        return Setup.class;
    }
}
