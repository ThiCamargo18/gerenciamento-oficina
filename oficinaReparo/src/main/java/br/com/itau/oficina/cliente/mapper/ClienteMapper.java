package br.com.itau.oficina.cliente.mapper;

import br.com.itau.oficina.cliente.model.ClienteEntity;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteEntity mapToEntity(ClienteEntrada clienteEntrada);

    ClienteSaida mapToSaida(ClienteEntity clienteEntity);
    @Mappings({
            @Mapping(source = "clienteEntity.id",target = "id"),
            @Mapping(source = "clienteEntrada.nome",target = "nome"),
            @Mapping(source = "clienteEntrada.cpf",target = "cpf"),
            @Mapping(source = "clienteEntrada.email",target = "email"),
            @Mapping(source = "clienteEntrada.telefone",target = "telefone")
    })
    ClienteEntity mapToEntityAtualizar(ClienteEntity clienteEntity, ClienteEntrada clienteEntrada);

    List<ClienteSaida> mapToSaidaList(List<ClienteEntity> clienteEntityList);
}
