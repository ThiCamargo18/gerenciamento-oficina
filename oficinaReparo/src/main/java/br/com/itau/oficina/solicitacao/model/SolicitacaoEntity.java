package br.com.itau.oficina.solicitacao.model;

import br.com.itau.oficina.peca.model.PecaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "solicitacao")
public class SolicitacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "cpfCliente")
    String cpfCliente;

    @Column(name = "modeloCarro")
    String modeloCarro;

    @NotNull
    @Column(name = "placaCarro")
    String placaCarro;

    @Lob
    String listaPeca;

    @Lob
    String servico;

    @Lob
    String servicoAdicional;

    @Column(name = "valorPecas")
    Double valorPecas;

    @Column(name = "valorTotal")
    Double valorTotal;

    @Column(name = "valorServicos")
    Double valorServicos;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    LocalDate dataEntrada;

    @JsonFormat(pattern="HH-mm-ss")
    @Column(name = "horaEntrada")
    LocalTime horaEntrada;

    @Column(name = "status")
    String status;
}
