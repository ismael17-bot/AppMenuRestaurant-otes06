package com.projectapi.lacuccina.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ITENS_PEDIDOS")
@Entity(name = "ITENS_PEDIDOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPedido;
    private Long idMenu;
    private int qtdItem;
    private float valor;
}
