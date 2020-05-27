package br.com.itau.oficina.solicitacao.facade;

import br.com.itau.oficina.cliente.facade.ClienteFacade;
import br.com.itau.oficina.cliente.model.ClienteEntity;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import br.com.itau.oficina.cliente.repository.ClienteRepository;
import br.com.itau.oficina.peca.facade.PecaFacade;
import br.com.itau.oficina.peca.model.PecaEntity;
import br.com.itau.oficina.solicitacao.mapper.SolicitacaoMapper;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntity;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoFiltros;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import br.com.itau.oficina.solicitacao.repository.SolicitacaoRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SolicitacaoFacade {
    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Autowired
    ClienteFacade clienteFacade;

    public SolicitacaoSaida salvar(SolicitacaoEntrada solicitacaoEntrada) throws Exception {
        solicitacaoEntrada = verificarDataEHorarioLocal(solicitacaoEntrada);

        double valorTotal = solicitacaoEntrada.getValorServicos() + solicitacaoEntrada.getValorPecas();

        SolicitacaoEntity solicitacaoEntity = SolicitacaoMapper.INSTANCE.mapToEntity(solicitacaoEntrada,valorTotal);

        solicitacaoEntity = solicitacaoRepository.save(solicitacaoEntity);

        SolicitacaoSaida solicitacaoSaida = SolicitacaoMapper.INSTANCE.mapToSaida(solicitacaoEntity);

        return solicitacaoSaida;
    }

    private SolicitacaoEntrada verificarDataEHorarioLocal(SolicitacaoEntrada solicitacaoEntrada) {
        LocalDate dataLocal = LocalDate.now();
        LocalTime horaLocal = LocalTime.now();

        solicitacaoEntrada.setDataEntrada(dataLocal);
        solicitacaoEntrada.setHoraEntrada(horaLocal);

        return solicitacaoEntrada;
    }


    public SolicitacaoSaida buscar(Long id) throws Exception {
        Optional<SolicitacaoEntity> solicitacaoEntity = solicitacaoRepository.findById(id);

        if (!solicitacaoEntity.isPresent()){
            throw new Exception("Cliente ou solicitação não encontrados! busque novamente.");
        }

        SolicitacaoEntity solicitacao = solicitacaoEntity.get();

        return SolicitacaoMapper.INSTANCE.mapToSaida(solicitacao);
    }

    public SolicitacaoSaida atualizar(Long id,SolicitacaoEntrada solicitacaoEntrada) throws Exception {
        SolicitacaoEntity solicitacaoEntity;

        Optional<SolicitacaoEntity> solicitacaoEntityOptional = solicitacaoRepository.findById(id);

        if(!solicitacaoEntityOptional.isPresent()){
            throw new Exception("Solicitação não encontrada! Busque novamente.");
        }

        solicitacaoEntity = solicitacaoEntityOptional.get();

//        List<PecaEntity> listaPeca = pecaFacade.buscarPecaEVerificarExistencia(solicitacaoEntrada.getListaPeca());
//
//        double valorPecas = calcularCustoPecas(listaPeca);

        double valorTotal = solicitacaoEntrada.getValorServicos() + solicitacaoEntrada.getValorPecas();

        solicitacaoEntity = SolicitacaoMapper.INSTANCE.mapToEntityList(solicitacaoEntity,valorTotal,solicitacaoEntrada);

        solicitacaoRepository.save(solicitacaoEntity);

        return SolicitacaoMapper.INSTANCE.mapToSaida(solicitacaoEntity);
    }

    public List<SolicitacaoSaida> buscarPorPeriodo(LocalDate dataInicio,LocalDate dataFim) throws Exception {
        List<SolicitacaoEntity> listaSolicitacaoEntity = solicitacaoRepository.findAllByDataEntradaBetween(dataInicio,dataFim);

        if(listaSolicitacaoEntity.isEmpty()){
            throw new Exception("Nenhuma solicitação encontrada nas datas informadas!");
        }

        return SolicitacaoMapper.INSTANCE.mapToSaidaList(listaSolicitacaoEntity);
    }

    public List<SolicitacaoSaida> buscarPorCpf(String cpf) throws Exception {
        List<SolicitacaoEntity> listaSolicitacao = solicitacaoRepository.findByCpfCliente(cpf);

        if(listaSolicitacao.isEmpty()){
            throw new Exception("Nenhuma solicitação encontrada pelo CPF informado!");
        }

        return SolicitacaoMapper.INSTANCE.mapToSaidaList(listaSolicitacao);
    }

    public List<SolicitacaoSaida> listar() {
        List<SolicitacaoEntity> solicitacaoEntityList = solicitacaoRepository.findAll();

        return SolicitacaoMapper.INSTANCE.mapToSaidaList(solicitacaoEntityList);
    }

    public Long calculoSaldoCaixaNosPeriodosInformados(List<SolicitacaoSaida> solicitacaoSaida) {
        Long lucroTotal = 0L;
        for(SolicitacaoSaida solicitacao : solicitacaoSaida){
            lucroTotal += solicitacao.getValorServicos().longValue();
        }
        return lucroTotal;
    }

    public SolicitacaoFiltros filtrarIndex(List<SolicitacaoSaida> saida) {
        Integer contadorAndamento=0;
        Integer contadorAguardandoPeca=0;
        Integer contadorFinalizado=0;
        for(SolicitacaoSaida solicitacao : saida){
            if(solicitacao.getStatus().equals("Aguardando Peças")){
                contadorAguardandoPeca++;
            }
            if(solicitacao.getStatus().equals("Reparando Veículo")){
                contadorAndamento++;
            }
            if(solicitacao.getStatus().equals("Finalizado")){
                contadorFinalizado++;
            }
        }
        SolicitacaoFiltros filtros = new SolicitacaoFiltros();
        filtros.setContadorAguardandoPeca(contadorAguardandoPeca);
        filtros.setContadorAndamento(contadorAndamento);
        filtros.setContadorFinalizado(contadorFinalizado);

        return filtros;
    }
}
