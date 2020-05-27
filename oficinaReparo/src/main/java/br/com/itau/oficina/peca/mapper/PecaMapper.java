package br.com.itau.oficina.peca.mapper;

import br.com.itau.oficina.peca.model.PecaEntity;
import br.com.itau.oficina.peca.model.PecaEntrada;
import br.com.itau.oficina.peca.model.PecaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PecaMapper {
    PecaMapper INSTANCE = Mappers.getMapper(PecaMapper.class);

    PecaEntity mapToEntity(PecaEntrada pecaEntrada);

    PecaSaida mapToSaida(PecaEntity pecaEntity);

    List<PecaSaida> mapToSaidaList(List<PecaEntity> listaEntity);
}
