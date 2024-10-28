package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.PedidoRepository;
import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.PedidoRequestDto;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDto;
import com.api_vendinha.api.domain.entities.Pedido;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoServiceInterface{
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(
            UserRepository userRepository,
            ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository
    ) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoResponseDto savePedido(PedidoRequestDto pedidoRequestDto) {
        Pedido pedido = new Pedido();

        Long userId = pedidoRequestDto.getUserId();
        Long produtoId = pedidoRequestDto.getProdutoId();
        Long qtdePedido = pedidoRequestDto.getQtde();

        User userExist = userRepository.findById(userId).orElseThrow();
        Produto produtoExist = produtoRepository.findById(produtoId).orElseThrow();

        Long qtdeProduto = produtoExist.getQtde();
        Double precoProduto = produtoExist.getPreco();

        if(qtdeProduto >= qtdePedido) {
            Long qtdeProdutoFinal = qtdeProduto - qtdePedido;
            Double precoPedido = qtdePedido * precoProduto;

            produtoExist.setQtde(qtdeProdutoFinal);
            produtoRepository.save(produtoExist);

            pedido.setUser(userExist);
            pedido.setProduto(produtoExist);
            pedido.setQtde(qtdePedido);
            pedido.setPreco(precoPedido);

            pedidoRepository.save(pedido);
        }

        return getPedidoResponse(pedido);
    }

    @Override
    public PedidoResponseDto getPedido(Long id) {
        Pedido pedidoExist = pedidoRepository.findById(id).orElseThrow();

        return getPedidoResponse(pedidoExist);
    }

    @Override
    public List<Pedido> getAllPedidos() { return pedidoRepository.findAll(); }

    private PedidoResponseDto getPedidoResponse(Pedido pedido) {
        PedidoResponseDto pedidoResponseDto = new PedidoResponseDto();

        pedidoResponseDto.setId(pedido.getId());
        pedidoResponseDto.setUser(pedido.getUser());
        pedidoResponseDto.setProduto(pedido.getProduto());
        pedidoResponseDto.setQtde(pedido.getQtde());
        pedidoResponseDto.setPreco(pedido.getPreco());

        return pedidoResponseDto;
    }
}
