package app.models.dao;

import app.models.ExternalPlace;
import app.models.Place;
import app.models.Theatre;
import libs.PlaceType;
import libs.mvc.models.ModelDAO;

import java.util.List;

public class PlaceDAO extends ModelDAO<Place, Integer>
{
    @Override
    public Class<Place> getModelClazz ()
    {
        return Place.class;
    }

    public List<Place> allOfType (PlaceType item)
    {
        String query = "FROM ";

        if (item == PlaceType.THEATRE) {
            query += Theatre.class.getSimpleName();
        }
        else {
            query += ExternalPlace.class.getSimpleName();
        }

        return em.createQuery(query).getResultList();
    }
}
