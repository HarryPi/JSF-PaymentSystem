package dao;

import java.util.List;

public interface DAO<E, K> {
    void persist(E entity);
    void remove(E entity);
    E findById(K id);
    List<E> getAll();

}
