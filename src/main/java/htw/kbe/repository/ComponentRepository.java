package htw.kbe.repository;

import htw.kbe.model.Component;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentRepository extends MongoRepository<Component, Integer> {
}
