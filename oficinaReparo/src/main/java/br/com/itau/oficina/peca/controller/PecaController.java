package br.com.itau.oficina.peca.controller;

import br.com.itau.oficina.peca.facade.PecaFacade;
import br.com.itau.oficina.peca.model.PecaEntrada;
import br.com.itau.oficina.peca.model.PecaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "oficina/gateway/peca", produces = "application/json")
@Configuration
@CrossOrigin
public class PecaController {
    @Autowired
    PecaFacade pecaFacade;

    @RequestMapping(value = "/paginaInicial")
    public ModelAndView paginaInicio() {
        return new ModelAndView("peca/inicio");
    }

    @RequestMapping(value = "/cadastrar")
    public ModelAndView criar() {
        return new ModelAndView("peca/criar");
    }

    @RequestMapping(value = "/buscarEspecifico")
    public ModelAndView buscarEspecifico() {
        return new ModelAndView("peca/selecionar");
    }

    @RequestMapping(value = "/atualizarEspecifica/{nome}")
    public ModelAndView buscarParaAtualizar(@PathVariable String nome) throws Exception {
        PecaSaida saida = pecaFacade.obterEspecifica(nome);
        ModelAndView mv = new ModelAndView("peca/atualizar");
        mv.addObject("especifica", saida);
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid PecaEntrada pecaEntrada) throws Exception {
        PecaSaida pecaSaida = pecaFacade.salvar(pecaEntrada);

        return new ModelAndView("sucesso");
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarPeca(@PathVariable Long id, @Valid PecaEntrada pecaEntrada) throws Exception {
        PecaSaida pecaSaida = pecaFacade.atualizar(id, pecaEntrada);

        return new ModelAndView("sucesso");
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        List<PecaSaida> saida = pecaFacade.listar();
        ModelAndView mv = new ModelAndView("peca/listar");
        mv.addObject("especifica", saida);
        return mv;
    }

    @GetMapping("/obter")
    public ModelAndView obter(@RequestParam String nome) {
        PecaSaida saida = pecaFacade.obterEspecifica(nome);
        ModelAndView mv = new ModelAndView("peca/buscar");
        mv.addObject("especifica", saida);
        return mv;
    }
}
