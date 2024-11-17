package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;

import java.util.List;

public interface ProdutoServiceInterface {
    ProdutoResponseDto saveProduto(ProdutoRequestDto produtoRequestDto);

    ProdutoResponseDto get(Long id);

    ProdutoResponseDto update(Long id, ProdutoRequestDto produtoRequestDto);

    List<ProdutoResponseDto> getAll();
}
