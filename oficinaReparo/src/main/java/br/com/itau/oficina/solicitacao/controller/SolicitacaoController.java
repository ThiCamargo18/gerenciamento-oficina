package br.com.itau.oficina.solicitacao.controller;

import br.com.itau.oficina.solicitacao.facade.SolicitacaoFacade;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoFiltros;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "oficina/gateway/solicitacao", produces = "application/json")
@Configuration
@CrossOrigin
public class SolicitacaoController {
    @Autowired
    SolicitacaoFacade solicitacaoFacade;

    @RequestMapping("/selecionarPeriodo")
    public ModelAndView selecionarPeriodo(){
        return new ModelAndView("solicitacao/selecionarPeriodo");
    }

    @RequestMapping("/periodoCaixa")
    public ModelAndView selecionarPeriodoCaixa(){
        return new ModelAndView("solicitacao/selecionarPeriodoCaixa");
    }

    @RequestMapping("/atualizarEspecifica/{id}")
    public ModelAndView  buscarParaAtualizar(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("solicitacao/atualizar");
        SolicitacaoSaida saida = solicitacaoFacade.buscar(id);
        mv.addObject("solicitacao",saida);
        return mv;
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid SolicitacaoEntrada entrada) throws Exception{
        SolicitacaoSaida saida = solicitacaoFacade.atualizar(id,entrada);


        return new ModelAndView("sucesso");
    }


    @GetMapping("/buscarPeriodo")
    public ModelAndView buscarPorPeriodo(@RequestParam("dataInicio") String dataInicio,
                                         @RequestParam("dataFim") String dataFim) throws Exception {

        LocalDate mes_inicio = LocalDate.parse(dataInicio);
        LocalDate mes_fim = LocalDate.parse(dataFim);
        List<SolicitacaoSaida> solicitacaoSaida = solicitacaoFacade.buscarPorPeriodo(mes_inicio,mes_fim);

        ModelAndView mv = new ModelAndView("solicitacao/buscarPeriodo");
        mv.addObject("listaSolicitacao",solicitacaoSaida);

        return mv;
    }

    @GetMapping("/valorCaixa")
    public ModelAndView buscarCaixa(@RequestParam("dataInicio") String dataInicio,
                                    @RequestParam("dataFim") String dataFim) throws Exception {
        LocalDate mes_inicio = LocalDate.parse(dataInicio);
        LocalDate mes_fim = LocalDate.parse(dataFim);
        List<SolicitacaoSaida> solicitacaoSaida = solicitacaoFacade.buscarPorPeriodo(mes_inicio,mes_fim);

        if(solicitacaoSaida.isEmpty()){
            throw new Exception("Nnehum reparo encontrado!");
        }

        Long resultado = solicitacaoFacade.calculoSaldoCaixaNosPeriodosInformados(solicitacaoSaida);

        ModelAndView mv = new ModelAndView("solicitacao/caixa");

        SolicitacaoFiltros filtros = new SolicitacaoFiltros();
        filtros.setCaixaPorPeriodo(resultado);
        filtros.setDataInicio(dataInicio);
        filtros.setDataFim(dataFim);
        mv.addObject("filtro",filtros);

        return mv;
    }
}
