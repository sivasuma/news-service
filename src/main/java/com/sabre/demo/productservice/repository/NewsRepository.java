package com.sabre.demo.productservice.repository;

import com.sabre.demo.productservice.entity.StroyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<StroyEntity,Long> {
}
