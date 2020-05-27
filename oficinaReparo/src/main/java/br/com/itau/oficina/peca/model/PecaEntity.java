package br.com.itau.oficina.peca.model;

import br.com.itau.oficina.solicitacao.model.SolicitacaoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "peca")
public class PecaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "valor")
    Double valor;

    @NotNull
    @Column(name = "nome")
    String nome;
    
}
