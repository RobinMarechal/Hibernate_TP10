package app.models.dao;

import app.models.ExternalPlace;
import app.models.Place;
import app.models.Theatre;
import libs.PlaceType;
import libs.mvc.models.ModelDAO;

import java.util.List;

/**
 * The DAO class for the Places
 */
public class PlaceDAO extends ModelDAO<Place, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Place> getModelClazz ()
    {
        return Place.class;
    }

    /**
     * Retrieve all places of type
     * @param type the type of the place (Theatre or ExternalPlace)
     * @return a List containing all the places of this type
     */
    public List<Place> allOfType (PlaceType type)
    {
        String query = "FROM ";

        if (type == PlaceType.THEATRE) {
            query += Theatre.class.getSimpleName();
        }
        else {
            query += ExternalPlace.class.getSimpleName();
        }

        return em.createQuery(query).getResultList();
    }
}
