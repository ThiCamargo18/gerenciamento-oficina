package br.com.itau.oficina.solicitacao.repository;

import br.com.itau.oficina.solicitacao.model.SolicitacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<SolicitacaoEntity,Long> {
    public List<SolicitacaoEntity> findByCpfCliente(String cpf);

    public List<SolicitacaoEntity> findByDataEntrada(LocalDate data);

    public List<SolicitacaoEntity> findAllByDataEntradaBetween(LocalDate atDay, LocalDate atEndOfMonth);
}
