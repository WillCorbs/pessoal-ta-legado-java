package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false) // Especifica que a coluna no banco de dados não pode ser nula.
    private String nome;

    @Column(name = "qtde")
    private Long qtde;

    @Column(name = "preco")
    private Double preco;
}
