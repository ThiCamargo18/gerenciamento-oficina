package br.com.itau.oficina.peca.repository;

import br.com.itau.oficina.peca.model.PecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PecaRepository  extends JpaRepository<PecaEntity,Long>{
    public PecaEntity findByNomeIgnoreCase(String nome);
}
