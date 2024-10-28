package com.api_vendinha.api.domain.dtos.response;

import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoResponseDto {
    private Long id;

    private User user;

    private Produto produto;

    private Long qtde;

    private Double preco;
}
