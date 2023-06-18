package com.projectapi.lacuccina.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projectapi.lacuccina.demo.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
