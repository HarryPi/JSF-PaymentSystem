package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<E, K> {
    void persist(E entity);
    void remove(E entity);
    Optional<E> findById(K id);
    List<E> getAll();

}
