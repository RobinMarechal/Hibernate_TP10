package libs.queries;

import libs.mvc.Model;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Query<T extends Model>
{
    private final EntityManager em;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> query;
    private Root<T> root;
    private Class<T> clazz;

    private List<Predicate> predicates;

    public Query (Class<T> clazz, EntityManager em)
    {
        this.clazz = clazz;

        this.em = em;
        this.criteriaBuilder = em.getCriteriaBuilder();
        this.query = criteriaBuilder.createQuery(clazz);
        this.root = query.from(clazz);
        this.predicates = new ArrayList<>();
    }

    public static <M extends Model> Query<M> forModel (Class<M> modelClass, EntityManager em)
    {
        return new Query<>(modelClass, em);
    }

    private Query<T> addWhereExpression (Predicate predicate)
    {
        this.predicates.add(predicate);
        return this;
    }

    public Query<T> whereGreater (String column, Number value)
    {
        return addWhereExpression(criteriaBuilder.gt(root.get(column), value));
    }

    public Query<T> whereGreaterOrEqual (String column, Number value)
    {
        return addWhereExpression(criteriaBuilder.ge(root.get(column), value));
    }

    public Query<T> whereLower (String column, Number value)
    {
        return addWhereExpression(criteriaBuilder.lt(root.get(column), value));
    }

    public Query<T> whereLowerOrEqual (String column, Number value)
    {
        return addWhereExpression(criteriaBuilder.le(root.get(column), value));
    }

    public Query<T> whereEqual (String column, Object value)
    {
        return addWhereExpression(criteriaBuilder.equal(root.get(column), value));
    }

    public Query<T> whereDifferent (String column, Object value)
    {
        return addWhereExpression(criteriaBuilder.notEqual(root.get(column), value));
    }

    public Query<T> whereLike (String column, String value)
    {
        return addWhereExpression(criteriaBuilder.like(root.get(column), value));
    }

    public Query<T> whereNotLike (String column, String value)
    {
        return addWhereExpression(criteriaBuilder.notLike(root.get(column), value));
    }

    public Query<T> whereBetween (String column, Object inf, Object sup)
    {
        return addWhereExpression(criteriaBuilder.between(root.get(column), inf.toString(), sup.toString()));
    }

    public Query<T> whereNull (String column)
    {
        return addWhereExpression(criteriaBuilder.isNull(root.get(column)));
    }

    public Query<T> whereNotNull (String column)
    {
        return addWhereExpression(criteriaBuilder.isNotNull(root.get(column)));
    }

    public List<T> get ()
    {
        Predicate[] predicatesArray = new Predicate[this.predicates.size()];
        predicates.toArray(predicatesArray);

        query.where(predicatesArray);

        return em.createQuery(query).getResultList();
    }

    public Query<T> where (String column, Operator operator, Object value)
    {
        if (operator == Operator.DIFFERENT) {
            operator = Operator.NOT_EQUAL;
        }

        switch (operator) {
            case EQUAL:
                return this.whereEqual(column, value);
            case GREATER_THAN:
                return this.whereGreater(column, (Number) value);
            case LOWER_THAN:
                return this.whereLower(column, (Number) value);
            case GREATER_EQUAL:
                return this.whereGreaterOrEqual(column, (Number) value);
            case LOWER_EQUAL:
                return this.whereLowerOrEqual(column, (Number) value);
            case NOT_EQUAL:
                return this.whereDifferent(column, value);
            case LIKE:
                return this.whereLike(column, (String) value);
            case NOT_LIKE:
                return this.whereNotLike(column, (String) value);
        }

        return this;
    }
}
