package com.projectapi.lacuccina.demo.repository;

import com.projectapi.lacuccina.demo.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItensPedido, Long> {
}
