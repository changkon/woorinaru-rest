package woorinaru.rest.service;

import woorinaru.core.command.UpdateCommand;

import java.util.List;
import java.util.Optional;

public interface GenericService<E> {
    void create(E e);
    Optional<E> get(int id);
    void delete(E e);
    void modify(E e);
    List<E> getAll();
}
