package com.projectapi.lacuccina.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectapi.lacuccina.demo.model.Menu;
import com.projectapi.lacuccina.demo.repository.MenuRepository;
@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public Menu create(Menu menu) {
        return this.menuRepository.save(menu);
    }

    public List<Menu> getAll() {
        return this.menuRepository.findAll();
    }

    public Menu update(String id, Menu menu) {
        Menu updated = this.menuRepository.findById(id).get();
        updated.setDesc(menu.getDesc());
        updated.setTitle(menu.getTitle());
        updated.setPrice(menu.getPrice());

        return this.menuRepository.save(updated);
    }

    public Menu delete(String id) {
        Menu menu = this.menuRepository.findById(id).get();
        if (menu != null)
            this.menuRepository.deleteById(id);
        return menu;
    }
}
