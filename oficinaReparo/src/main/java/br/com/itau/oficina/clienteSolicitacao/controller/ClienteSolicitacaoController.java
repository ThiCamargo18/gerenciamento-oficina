package br.com.itau.oficina.clienteSolicitacao.controller;

import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.clienteSolicitacao.facade.ClienteSolicitacaoFacade;
import br.com.itau.oficina.clienteSolicitacao.model.ClienteSolicitacaoEntrada;
import br.com.itau.oficina.clienteSolicitacao.model.ClienteSolicitacaoSaida;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "oficina/gateway/clientes/solicitacoes", produces = "application/json")
@Configuration
@CrossOrigin
public class ClienteSolicitacaoController {
    @Autowired
    ClienteSolicitacaoFacade clienteSolicitacaoFacade;

    @RequestMapping(value = "/orcamento")
    public ModelAndView criar(){
        ModelAndView mv = new ModelAndView("clienteSolicitacao/criar");
        ClienteEntrada cliente = new ClienteEntrada();
        List<SolicitacaoEntrada> listaSolicitacao = new ArrayList<>();
        mv.addObject("cliente", cliente);
        mv.addObject("solicitacao",listaSolicitacao);
        return mv;
    }

    @RequestMapping(value = "/selecionar")
    public ModelAndView selecionar(){
        return new ModelAndView("clienteSolicitacao/selecionar");
    }

    @PostMapping("/orcamento")
    public ModelAndView solicitarOrcamento(@Valid ClienteSolicitacaoEntrada entrada) throws Exception {
        ClienteSolicitacaoSaida saida = clienteSolicitacaoFacade.salvar(entrada);

        return new ModelAndView("peca/inicio");
    }


    @GetMapping("/buscarPorId")
    public ModelAndView buscarSolicitacoesPorId(@RequestParam Long id) throws Exception {
        ClienteSolicitacaoSaida saida = clienteSolicitacaoFacade.buscar(id);
        List<SolicitacaoSaida> solicitacaoSaida = new ArrayList<>();
        int size = saida.getSolicitacao().size();

        for(int i=0;i<size;i++){
            solicitacaoSaida.add(saida.getSolicitacao().get(i));
        }

        ModelAndView mv = new ModelAndView("clienteSolicitacao/buscar");
        mv.addObject("saida",saida);
        mv.addObject("listaSolicitacao",solicitacaoSaida);
        return mv;
    }

    @GetMapping("/buscarPorCpf")
    public ModelAndView buscarSolicitacoesPorCpfCliente(@RequestParam String cpf) throws Exception {
        ClienteSolicitacaoSaida saida = clienteSolicitacaoFacade.buscarCpf(cpf);

        List<SolicitacaoSaida> solicitacaoSaida = new ArrayList<>();
        int size = saida.getSolicitacao().size();

        for(int i=0;i<size;i++){
            solicitacaoSaida.add(saida.getSolicitacao().get(i));
        }

        ModelAndView mv = new ModelAndView("clienteSolicitacao/buscar");
        mv.addObject("saida",saida);
        mv.addObject("listaSolicitacao",solicitacaoSaida);
        return mv;
    }
}
