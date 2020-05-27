package br.com.itau.oficina.solicitacao.model;

import br.com.itau.oficina.cliente.model.ClienteEntity;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class SolicitacaoEntrada {
    private String status;
    private String cpfCliente;
    private String modeloCarro;
    private String placaCarro;
    private String listaPeca;
    private String servico;
    private String servicoAdicional;
    private Double valorServicos;
    private Double valorPecas;
    private Double valorTotal;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataEntrada;
    @JsonFormat(pattern="HH-mm-ss")
    private LocalTime horaEntrada;
}
