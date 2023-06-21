package com.projectapi.lacuccina.demo.controller;

import com.projectapi.lacuccina.demo.DTO.PedidoDTO;
import com.projectapi.lacuccina.demo.DTO.PedidoRequestDTO;
import com.projectapi.lacuccina.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> getAll() {
        return pedidoService.getAllOrders();
    }

    @GetMapping("/{id}")
    public PedidoDTO getEspecficOrderById(@PathVariable Long id) {
        return pedidoService.getOrder(id);
    }

    @PostMapping
    public void addToOrder(@RequestBody PedidoRequestDTO pedido) {
        pedidoService.addToOrder(pedido.id(), pedido.menuId(), pedido.qtd());
    }

    @DeleteMapping
    public void removeFromOrder(@RequestBody PedidoRequestDTO pedido) {
        pedidoService.removeFromOrder(pedido.id(), pedido.menuId());
    }
}
