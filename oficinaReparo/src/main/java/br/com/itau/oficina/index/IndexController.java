package br.com.itau.oficina.index;


import br.com.itau.oficina.solicitacao.facade.SolicitacaoFacade;
import br.com.itau.oficina.solicitacao.model.SolicitacaoFiltros;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping
@Configuration
@CrossOrigin
public class IndexController {
    @Autowired
    SolicitacaoFacade solicitacaoFacade;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        List<SolicitacaoSaida> saida = solicitacaoFacade.listar();
        SolicitacaoFiltros filtros = solicitacaoFacade.filtrarIndex(saida);
        mv.addObject("filtro",filtros);
        return mv;
    }
}