package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class JpaDao<E, K> implements DAO<E, K> {
    protected Class entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
        // Using reflection we get the entity class
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    public void persist(E entity) {
        this.entityManager.persist(entity);
    }

    public void remove(E entity) {
        this.entityManager.remove(entity);
    }

    public E findById(K id) {
        return (E) this.entityManager.find(entityClass, id);
    }

    @Override
    public List<E> getAll() {
        return  (List<E>) this.entityManager.createQuery(
                "select e from " + entityClass.getSimpleName() + " e"
        ).getResultList();
    }
}
