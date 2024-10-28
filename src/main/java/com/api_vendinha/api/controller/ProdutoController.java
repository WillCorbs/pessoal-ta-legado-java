package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoServiceInterface produtoService;

    @Autowired
    public ProdutoController(ProdutoServiceInterface produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoResponseDto saveProduto(@RequestBody ProdutoRequestDto produtoRequestDto) {
        return produtoService.saveProduto(produtoRequestDto);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto get(
            @PathVariable Long id
    ) {
        return produtoService.get(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDto update(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDto produtoRequestDto
    ) {
        return produtoService.update(id, produtoRequestDto);
    }

    @GetMapping
    public List<Produto> getAll() {
        return produtoService.getAll();
    }
}
