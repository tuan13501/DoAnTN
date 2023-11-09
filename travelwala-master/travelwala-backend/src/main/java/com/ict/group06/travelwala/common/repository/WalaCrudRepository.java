package com.ict.group06.travelwala.common.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface WalaCrudRepository<T, ID> {
    <S extends T> S  save(S entity);

    <S extends T> List<S> saveAll(List<S> entities);

    List<T> findAll();

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    long count();

    void deleteById(ID id);
}
