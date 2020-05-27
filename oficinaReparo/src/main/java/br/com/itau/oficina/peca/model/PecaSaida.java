package br.com.itau.oficina.peca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PecaSaida {
    private Long id;
    private Double valor;
    private String nome;
}
