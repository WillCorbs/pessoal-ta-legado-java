package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoRequestDto {
    private String nome;

    private Long qtde;

    private Double preco;
}
