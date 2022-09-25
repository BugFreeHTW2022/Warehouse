package htw.kbe.repository;

import htw.kbe.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer>{

}
