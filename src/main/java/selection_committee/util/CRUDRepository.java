package selection_committee.util;

import java.util.List;

public interface CRUDRepository<E> {

    List<E> getAll();

    E getById(int id);

    E create(E e);

    E update(int id, E e);

    void delete(int id);
}
