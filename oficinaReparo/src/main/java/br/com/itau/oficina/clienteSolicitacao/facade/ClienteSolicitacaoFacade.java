package br.com.itau.oficina.clienteSolicitacao.facade;


import br.com.itau.oficina.cliente.facade.ClienteFacade;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import br.com.itau.oficina.clienteSolicitacao.model.ClienteSolicitacaoEntrada;
import br.com.itau.oficina.clienteSolicitacao.model.ClienteSolicitacaoSaida;
import br.com.itau.oficina.solicitacao.facade.SolicitacaoFacade;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteSolicitacaoFacade {
    @Autowired
    ClienteFacade clienteFacade;
    @Autowired
    SolicitacaoFacade solicitacaoFacade;

    public ClienteSolicitacaoSaida salvar(ClienteSolicitacaoEntrada entrada) throws Exception {
        ClienteSolicitacaoSaida saida = new ClienteSolicitacaoSaida();
        List<SolicitacaoSaida> listaSolicitacao = new ArrayList<>();
        ClienteSaida cliente = clienteFacade.obter(entrada.getCliente().getCpf());
        saida.setCliente(cliente);
        entrada.getSolicitacao().get(0).setCpfCliente(cliente.getCpf());
        SolicitacaoSaida solicitacao = solicitacaoFacade.salvar(entrada.getSolicitacao().get(0));

        listaSolicitacao.add(solicitacao);

        saida.setSolicitacao(listaSolicitacao);

        return saida;
    }

    public ClienteSolicitacaoSaida atualizar(Long id, ClienteSolicitacaoEntrada entrada) throws Exception {
        ClienteSolicitacaoSaida saida = new ClienteSolicitacaoSaida();
        List<SolicitacaoSaida> listaSolicitacao = new ArrayList<>();
        SolicitacaoSaida solicitacao;

        ClienteSaida cliente = clienteFacade.obter(entrada.getSolicitacao().get(0).getCpfCliente());
        SolicitacaoSaida solicitacaoAntiga = solicitacaoFacade.buscar(id);
        solicitacao = solicitacaoFacade.atualizar(id,entrada.getSolicitacao().get(0));

        listaSolicitacao.add(solicitacao);

        saida.setSolicitacao(listaSolicitacao);
        saida.setCliente(cliente);

        return saida;
    }


    public ClienteSolicitacaoSaida buscar(Long id) throws Exception {
        ClienteSolicitacaoSaida saida = new ClienteSolicitacaoSaida();
        List<SolicitacaoSaida> listaSolicitacao = new ArrayList<>();
        SolicitacaoSaida solicitacao = solicitacaoFacade.buscar(id);

        ClienteSaida cliente = clienteFacade.obter(solicitacao.getCpfCliente());

        saida.setCliente(cliente);
        listaSolicitacao.add(solicitacao);
        saida.setSolicitacao(listaSolicitacao);
        return saida;
    }

    public ClienteSolicitacaoSaida buscarCpf(String cpf) throws Exception {
        ClienteSolicitacaoSaida saida = new ClienteSolicitacaoSaida();

        List<SolicitacaoSaida> solicitacao = solicitacaoFacade.buscarPorCpf(cpf);

        ClienteSaida cliente = clienteFacade.obter(cpf);

        saida.setCliente(cliente);

        saida.setSolicitacao(solicitacao);
        return saida;

    }
}
