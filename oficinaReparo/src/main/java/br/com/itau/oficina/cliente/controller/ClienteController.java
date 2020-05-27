package br.com.itau.oficina.cliente.controller;

import br.com.itau.oficina.cliente.facade.ClienteFacade;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "oficina/gateway/cliente", produces = "application/json")
@Configuration
@CrossOrigin
public class ClienteController {
    @Autowired
    ClienteFacade clienteFacade;

    @RequestMapping(value = "/cadastrar")
    public ModelAndView  criar(){
        return new ModelAndView("cliente/criar");
    }

    @RequestMapping(value = "/buscarEspecifico")
    public ModelAndView  buscarEspecifico(){
        return new ModelAndView("cliente/selecionar");
    }

    @RequestMapping(value = "/atualizarEspecifico/{cpf}")
    public ModelAndView  buscarParaAtualizar(@PathVariable String cpf) throws Exception {
        ModelAndView mv = new ModelAndView("cliente/atualizar");
        ClienteSaida saida = clienteFacade.obter(cpf);
        mv.addObject("cliente",saida);
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid ClienteEntrada entrada) throws Exception {
        ClienteSaida saida = clienteFacade.salvar(entrada);

        return new ModelAndView("sucesso");
    }

    @PostMapping("/atualizar/{cpf}")
    public ModelAndView atualizar(@PathVariable String cpf, @Valid ClienteEntrada entrada) throws Exception {
        ClienteSaida saida = clienteFacade.atualizar(cpf,entrada);

        return new ModelAndView("sucesso");
    }

    @GetMapping("/obter")
    public ModelAndView obter(@RequestParam String cpf) throws Exception {
        ClienteSaida saida = clienteFacade.obter(cpf);
        ModelAndView mv = new ModelAndView("cliente/buscar");
        mv.addObject("especifico",saida);
        return mv;
    }

    @GetMapping(value="/listar")
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("cliente/listar");
        List<ClienteSaida> clienteSaidas = clienteFacade.listar();
        mv.addObject("especifico", clienteSaidas);
        return mv;
    }
}
