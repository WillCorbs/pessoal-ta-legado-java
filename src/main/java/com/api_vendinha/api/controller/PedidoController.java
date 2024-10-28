package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.PedidoRequestDto;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDto;
import com.api_vendinha.api.domain.entities.Pedido;
import com.api_vendinha.api.domain.service.PedidoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoServiceInterface pedidoService;

    @Autowired
    public PedidoController(PedidoServiceInterface pedidoService) { this.pedidoService = pedidoService; }

    @PostMapping
    public PedidoResponseDto savePedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        return pedidoService.savePedido(pedidoRequestDto);
    }

    @GetMapping("/{id}")
    public PedidoResponseDto getPedido(
            @PathVariable Long id
    ) {
        return pedidoService.getPedido(id);
    }

    @GetMapping
    public List<Pedido> getAllPedidos() { return pedidoService.getAllPedidos(); }
}
