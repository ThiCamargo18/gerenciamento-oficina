package br.com.itau.oficina.clienteSolicitacao.model;


import br.com.itau.oficina.cliente.model.ClienteSaida;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSolicitacaoSaida {
    ClienteSaida cliente;
    List<SolicitacaoSaida> solicitacao = new ArrayList<>();
}
