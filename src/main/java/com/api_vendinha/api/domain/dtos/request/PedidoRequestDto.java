package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoRequestDto {
    private Long userId;

    private Long produtoId;

    private Long qtde;

    private Double preco;
}
