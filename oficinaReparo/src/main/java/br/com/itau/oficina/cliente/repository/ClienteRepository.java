package br.com.itau.oficina.cliente.repository;

import br.com.itau.oficina.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    public Optional<ClienteEntity> findByCpf(String cpf);
}
