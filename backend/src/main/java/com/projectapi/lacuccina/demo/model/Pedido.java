package com.projectapi.lacuccina.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "PEDIDO")
@Entity(name = "PEDIDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mesa;
    private String dtPedido;
    private String hrPedido;
    private String status;
    private int qtdItens;
    private float valor;
}
