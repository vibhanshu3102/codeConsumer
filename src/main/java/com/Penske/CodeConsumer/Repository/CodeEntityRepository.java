package com.Penske.CodeConsumer.Repository;

import com.Penske.CodeConsumer.Model.CodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeEntityRepository extends MongoRepository<CodeEntity, String> {

}