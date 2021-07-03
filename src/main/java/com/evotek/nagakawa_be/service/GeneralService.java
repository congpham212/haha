package com.evotek.nagakawa_be.service;

import java.util.List;

public interface GeneralService<T> {
    List<T> findAll();

    T save(T t);

    T findById(Long id);

    void update(Long id, T T);

    void remove(Long id);
}
