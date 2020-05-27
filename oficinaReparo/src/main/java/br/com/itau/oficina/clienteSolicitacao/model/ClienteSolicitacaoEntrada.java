package br.com.itau.oficina.clienteSolicitacao.model;


import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
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
public class ClienteSolicitacaoEntrada {
    ClienteEntrada cliente;
    List<SolicitacaoEntrada> solicitacao = new ArrayList<>();
}
