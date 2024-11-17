package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoResponseDto saveProduto(ProdutoRequestDto produtoRequestDto) {
        Produto produto = new Produto();

        return getProdutoResponse(setProdutoAttributes(produto, produtoRequestDto));
    }

    @Override
    public ProdutoResponseDto get(Long id) {
        Produto produtoExist = produtoRepository.findById(id).orElseThrow();

        return getProdutoResponse(produtoExist);
    }

    @Override
    public ProdutoResponseDto update(Long id, ProdutoRequestDto produtoRequestDto) {
        Produto produtoExist = produtoRepository.findById(id).orElseThrow();

        return getProdutoResponse(setProdutoAttributes(produtoExist, produtoRequestDto));
    }

    @Override
    public List<ProdutoResponseDto> getAll() {
        return produtoRepository.findAll().stream().map(this::getProdutoResponse).toList();
    }


    private Produto setProdutoAttributes(Produto produto, ProdutoRequestDto produtoRequestDto) {
        produto.setNome(produtoRequestDto.getNome());
        produto.setQtde(produtoRequestDto.getQtde());
        produto.setPreco(produtoRequestDto.getPreco());

        produtoRepository.save(produto);

        return produto;
    }

    private ProdutoResponseDto getProdutoResponse(Produto produto) {
        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();

        produtoResponseDto.setId(produto.getId());
        produtoResponseDto.setNome(produto.getNome());
        produtoResponseDto.setQtde(produto.getQtde());
        produtoResponseDto.setPreco(produto.getPreco());

        return produtoResponseDto;
    }
}
