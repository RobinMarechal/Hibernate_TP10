package app.models.dao;

import app.models.Place;
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
        // TODO : move the code to the DAO
        String query = "FROM ";

        if (item == PlaceType.THEATRE) {
            query += "Threatre";
        }
        else {
            query += "ExternalPlace";
        }

        return em.createQuery(query).getResultList();
    }
}
