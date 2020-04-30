package dao;

import java.util.List;

public interface DAO<E, K> {
    void persist(E entity);
    void remove(K id);
    E findById(K id);
    List<E> getAll();

}
