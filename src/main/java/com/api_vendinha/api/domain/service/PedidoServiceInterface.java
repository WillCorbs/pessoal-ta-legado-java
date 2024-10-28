package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.PedidoRequestDto;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDto;
import com.api_vendinha.api.domain.entities.Pedido;
import java.util.List;

public interface PedidoServiceInterface {
    PedidoResponseDto savePedido(PedidoRequestDto pedidoRequestDto);

    PedidoResponseDto getPedido(Long id);

    List<Pedido> getAllPedidos();
}
