package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
