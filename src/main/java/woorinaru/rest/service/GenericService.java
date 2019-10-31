package woorinaru.rest.service;

import java.util.List;

public interface GenericService<E> {
    int create(E e);
    E get(int id);
    void delete(E e);
    void modify(E e);
    List<E> getAll();
}
