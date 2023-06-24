package com.projectapi.lacuccina.demo.DTO;

import com.projectapi.lacuccina.demo.model.ItensPedido;
public record ItensPedidoDTO(Long id, Long idPedido, Long idMenu, Integer qtdItem, Float valor) {
    public ItensPedidoDTO(ItensPedido pedido){
        this(pedido.getId(), pedido.getIdpedido(), pedido.getIdmenu(), pedido.getQtditem(), pedido.getValor());
    }
}