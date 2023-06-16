package com.projectapi.lacuccina.demo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.projectapi.lacuccina.demo.model.Menu;

public interface MenuRepository extends MongoRepository<Menu, String> {
}
