package br.com.itau.oficina.solicitacao.mapper;

import br.com.itau.oficina.peca.model.PecaEntity;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntity;
import br.com.itau.oficina.solicitacao.model.SolicitacaoEntrada;
import br.com.itau.oficina.solicitacao.model.SolicitacaoSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SolicitacaoMapper {
    SolicitacaoMapper INSTANCE = Mappers.getMapper(SolicitacaoMapper.class);

    @Mappings({
            @Mapping(source = "valorTotal", target = "valorTotal")
    })
    SolicitacaoEntity mapToEntity(SolicitacaoEntrada solicitacaoEntrada, Double valorTotal);

    @Mappings({
            @Mapping(source = "solicitacaoEntity.dataEntrada", target = "dataEntrada"),
            @Mapping(source = "solicitacaoEntity.horaEntrada", target = "horaEntrada")
    })
    SolicitacaoSaida mapToSaida(SolicitacaoEntity solicitacaoEntity);

    List<SolicitacaoSaida> mapToSaidaList(List<SolicitacaoEntity> listaSolicitacaoEntity);

    @Mappings({
            @Mapping(source = "solicitacaoEntrada.listaPeca", target = "listaPeca"),
            @Mapping(source = "solicitacaoEntrada.servico", target = "servico"),
            @Mapping(source = "solicitacaoEntrada.servicoAdicional", target = "servicoAdicional"),
            @Mapping(source = "solicitacaoEntrada.cpfCliente", target = "cpfCliente"),
            @Mapping(source = "solicitacaoEntrada.modeloCarro", target = "modeloCarro"),
            @Mapping(source = "solicitacaoEntrada.placaCarro", target = "placaCarro"),
            @Mapping(source = "solicitacaoEntrada.valorServicos", target = "valorServicos"),
            @Mapping(source = "solicitacaoEntrada.valorPecas", target = "valorPecas"),
            @Mapping(source = "valorTotal", target = "valorTotal"),
            @Mapping(source = "solicitacaoOptional.dataEntrada", target = "dataEntrada"),
            @Mapping(source = "solicitacaoOptional.horaEntrada", target = "horaEntrada"),
            @Mapping(source = "solicitacaoEntrada.status", target = "status")
    })
    SolicitacaoEntity mapToEntityList(SolicitacaoEntity solicitacaoOptional, Double valorTotal, SolicitacaoEntrada solicitacaoEntrada);
}
