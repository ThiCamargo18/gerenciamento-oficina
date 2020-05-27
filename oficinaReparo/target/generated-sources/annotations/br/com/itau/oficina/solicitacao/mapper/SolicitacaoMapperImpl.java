package br.com.itau.oficina.solicitacao.mapper;

import br.com.itau.oficina.solicitacao.model.SolicitacaoEntity;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-27T18:57:48-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class SolicitacaoMapperImpl implements SolicitacaoMapper {

    @Override
    public SolicitacaoEntity mapToEntity(SolicitacaoEntrada solicitacaoEntrada, Double valorTotal) {
        if ( solicitacaoEntrada == null && valorTotal == null ) {
            return null;
        }

        SolicitacaoEntity solicitacaoEntity = new SolicitacaoEntity();

        if ( solicitacaoEntrada != null ) {
            solicitacaoEntity.setCpfCliente( solicitacaoEntrada.getCpfCliente() );
            solicitacaoEntity.setModeloCarro( solicitacaoEntrada.getModeloCarro() );
            solicitacaoEntity.setPlacaCarro( solicitacaoEntrada.getPlacaCarro() );
            solicitacaoEntity.setListaPeca( solicitacaoEntrada.getListaPeca() );
            solicitacaoEntity.setServico( solicitacaoEntrada.getServico() );
            solicitacaoEntity.setServicoAdicional( solicitacaoEntrada.getServicoAdicional() );
            solicitacaoEntity.setValorPecas( solicitacaoEntrada.getValorPecas() );
            solicitacaoEntity.setValorServicos( solicitacaoEntrada.getValorServicos() );
            solicitacaoEntity.setDataEntrada( solicitacaoEntrada.getDataEntrada() );
            solicitacaoEntity.setHoraEntrada( solicitacaoEntrada.getHoraEntrada() );
            solicitacaoEntity.setStatus( solicitacaoEntrada.getStatus() );
        }
        if ( valorTotal != null ) {
            solicitacaoEntity.setValorTotal( valorTotal );
        }

        return solicitacaoEntity;
    }

    @Override
    public SolicitacaoSaida mapToSaida(SolicitacaoEntity solicitacaoEntity) {
        if ( solicitacaoEntity == null ) {
            return null;
        }

        SolicitacaoSaida solicitacaoSaida = new SolicitacaoSaida();

        solicitacaoSaida.setDataEntrada( solicitacaoEntity.getDataEntrada() );
        solicitacaoSaida.setHoraEntrada( solicitacaoEntity.getHoraEntrada() );
        solicitacaoSaida.setId( solicitacaoEntity.getId() );
        solicitacaoSaida.setStatus( solicitacaoEntity.getStatus() );
        solicitacaoSaida.setCpfCliente( solicitacaoEntity.getCpfCliente() );
        solicitacaoSaida.setModeloCarro( solicitacaoEntity.getModeloCarro() );
        solicitacaoSaida.setPlacaCarro( solicitacaoEntity.getPlacaCarro() );
        solicitacaoSaida.setListaPeca( solicitacaoEntity.getListaPeca() );
        solicitacaoSaida.setServico( solicitacaoEntity.getServico() );
        solicitacaoSaida.setServicoAdicional( solicitacaoEntity.getServicoAdicional() );
        solicitacaoSaida.setValorPecas( solicitacaoEntity.getValorPecas() );
        solicitacaoSaida.setValorServicos( solicitacaoEntity.getValorServicos() );
        solicitacaoSaida.setValorTotal( solicitacaoEntity.getValorTotal() );

        return solicitacaoSaida;
    }

    @Override
    public List<SolicitacaoSaida> mapToSaidaList(List<SolicitacaoEntity> listaSolicitacaoEntity) {
        if ( listaSolicitacaoEntity == null ) {
            return null;
        }

        List<SolicitacaoSaida> list = new ArrayList<SolicitacaoSaida>( listaSolicitacaoEntity.size() );
        for ( SolicitacaoEntity solicitacaoEntity : listaSolicitacaoEntity ) {
            list.add( mapToSaida( solicitacaoEntity ) );
        }

        return list;
    }

    @Override
    public SolicitacaoEntity mapToEntityList(SolicitacaoEntity solicitacaoOptional, Double valorTotal, SolicitacaoEntrada solicitacaoEntrada) {
        if ( solicitacaoOptional == null && valorTotal == null && solicitacaoEntrada == null ) {
            return null;
        }

        SolicitacaoEntity solicitacaoEntity = new SolicitacaoEntity();

        if ( solicitacaoOptional != null ) {
            solicitacaoEntity.setHoraEntrada( solicitacaoOptional.getHoraEntrada() );
            solicitacaoEntity.setDataEntrada( solicitacaoOptional.getDataEntrada() );
            solicitacaoEntity.setId( solicitacaoOptional.getId() );
        }
        if ( valorTotal != null ) {
            solicitacaoEntity.setValorTotal( valorTotal );
        }
        if ( solicitacaoEntrada != null ) {
            solicitacaoEntity.setServicoAdicional( solicitacaoEntrada.getServicoAdicional() );
            solicitacaoEntity.setListaPeca( solicitacaoEntrada.getListaPeca() );
            solicitacaoEntity.setValorPecas( solicitacaoEntrada.getValorPecas() );
            solicitacaoEntity.setValorServicos( solicitacaoEntrada.getValorServicos() );
            solicitacaoEntity.setPlacaCarro( solicitacaoEntrada.getPlacaCarro() );
            solicitacaoEntity.setCpfCliente( solicitacaoEntrada.getCpfCliente() );
            solicitacaoEntity.setModeloCarro( solicitacaoEntrada.getModeloCarro() );
            solicitacaoEntity.setServico( solicitacaoEntrada.getServico() );
            solicitacaoEntity.setStatus( solicitacaoEntrada.getStatus() );
        }

        return solicitacaoEntity;
    }
}
