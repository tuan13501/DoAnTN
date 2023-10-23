package com.tuandh.travelwala.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WalaRepositoryImpl<T, ID> implements WalaRepository<T, ID> {
    protected final MongoTemplate mongoTemplate;
    private final Class<T> typeParameterClass;

    @Override
    public <S extends T> S save(S entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(List<S> entities) {
        return entities
                .stream()
                .map(mongoTemplate::save)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(this.typeParameterClass);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        long count = this.count();
        List<T> list = this.findAll((new Query()).with(pageable));
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(mongoTemplate.findById(id, this.typeParameterClass));
    }

    @Override
    public boolean existsById(ID id) {
        return mongoTemplate.exists(this.getIdQuery(id), this.typeParameterClass);
    }

    @Override
    public long count() {
        return mongoTemplate.count(new Query(), this.typeParameterClass);
    }

    @Override
    public void deleteById(ID id) {
        mongoTemplate.remove(getIdQuery(id), this.typeParameterClass);
    }

    private List<T> findAll(@Nullable Query query) {
        return query == null ? Collections.emptyList() : this.mongoTemplate.find(query, this.typeParameterClass);
    }

    private Query getIdQuery(ID id) {
        return new Query(Criteria.where("id").is(id));
    }
}
