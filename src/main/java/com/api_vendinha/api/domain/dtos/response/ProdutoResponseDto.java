package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoResponseDto {
    private Long id;
    private String nome;
    private Long qtde;
    private Double preco;
}
