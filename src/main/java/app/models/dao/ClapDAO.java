package app.models.dao;

import app.models.Clap;
import app.models.pk.ClapPrimaryKey;
import libs.mvc.models.ModelDAO;

public class ClapDAO extends ModelDAO<Clap, ClapPrimaryKey>
{
    @Override
    public Class<Clap> getModelClazz ()
    {
        return Clap.class;
    }
}
