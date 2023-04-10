 package fa.training.assignment2.servcie;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T t);
    int update(T t);
    boolean delete(T t);
}
