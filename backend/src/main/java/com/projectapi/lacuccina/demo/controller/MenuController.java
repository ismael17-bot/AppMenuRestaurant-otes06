package com.projectapi.lacuccina.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectapi.lacuccina.demo.model.Menu;
import com.projectapi.lacuccina.demo.service.MenuService;
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> getAll() {
        return this.menuService.getAll();
    }

    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return this.menuService.create(menu);
    }

    @PutMapping(value = "/{id}")
    public Menu update(@PathVariable("id") String id, @RequestBody Menu menu) {
        return this.menuService.update(id, menu);
    }

    @DeleteMapping(value = "/{id}")
    public Menu delete(@PathVariable("id") String id) {
        return this.menuService.delete(id);
    }
}
