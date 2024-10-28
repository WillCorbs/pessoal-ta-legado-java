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

        produto.setNome(produtoRequestDto.getNome());
        produto.setQtde(produtoRequestDto.getQtde());
        produto.setPreco(produtoRequestDto.getPreco());

        Produto savedProduto = produtoRepository.save(produto);

        return getProdutoResponse(savedProduto);
    }

    @Override
    public ProdutoResponseDto get(Long id) {
        Produto produtoExist = produtoRepository.findById(id).orElseThrow();

        return getProdutoResponse(produtoExist);
    }

    @Override
    public ProdutoResponseDto update(Long id, ProdutoRequestDto produtoRequestDto) {
        Produto produtoExist = produtoRepository.findById(id).orElseThrow();
        produtoExist.setNome(produtoRequestDto.getNome());
        produtoExist.setQtde(produtoRequestDto.getQtde());
        produtoExist.setPreco(produtoRequestDto.getPreco());

        produtoRepository.save(produtoExist);

        return getProdutoResponse(produtoExist);
    }

    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
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
