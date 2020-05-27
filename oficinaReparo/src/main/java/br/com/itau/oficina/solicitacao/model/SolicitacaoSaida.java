package br.com.itau.oficina.solicitacao.model;

import br.com.itau.oficina.peca.model.PecaSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoSaida {
    private Long id;
    private String status;
    private String cpfCliente;
    private String modeloCarro;
    private String placaCarro;
    private String listaPeca;
    private String servico;
    private String servicoAdicional;
    private Double valorPecas;
    private Double valorServicos;
    private Double valorTotal;
    private LocalDate dataEntrada;
    private LocalTime horaEntrada;
}
