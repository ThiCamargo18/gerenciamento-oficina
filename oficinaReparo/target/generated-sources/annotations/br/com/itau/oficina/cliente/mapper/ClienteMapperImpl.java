package br.com.itau.oficina.cliente.mapper;

import br.com.itau.oficina.cliente.model.ClienteEntity;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-27T18:57:48-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity mapToEntity(ClienteEntrada clienteEntrada) {
        if ( clienteEntrada == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome( clienteEntrada.getNome() );
        clienteEntity.setCpf( clienteEntrada.getCpf() );
        clienteEntity.setEmail( clienteEntrada.getEmail() );
        clienteEntity.setTelefone( clienteEntrada.getTelefone() );

        return clienteEntity;
    }

    @Override
    public ClienteSaida mapToSaida(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        ClienteSaida clienteSaida = new ClienteSaida();

        clienteSaida.setId( clienteEntity.getId() );
        clienteSaida.setNome( clienteEntity.getNome() );
        clienteSaida.setCpf( clienteEntity.getCpf() );
        clienteSaida.setEmail( clienteEntity.getEmail() );
        clienteSaida.setTelefone( clienteEntity.getTelefone() );

        return clienteSaida;
    }

    @Override
    public ClienteEntity mapToEntityAtualizar(ClienteEntity clienteEntity, ClienteEntrada clienteEntrada) {
        if ( clienteEntity == null && clienteEntrada == null ) {
            return null;
        }

        ClienteEntity clienteEntity1 = new ClienteEntity();

        if ( clienteEntity != null ) {
            clienteEntity1.setId( clienteEntity.getId() );
        }
        if ( clienteEntrada != null ) {
            clienteEntity1.setCpf( clienteEntrada.getCpf() );
            clienteEntity1.setTelefone( clienteEntrada.getTelefone() );
            clienteEntity1.setNome( clienteEntrada.getNome() );
            clienteEntity1.setEmail( clienteEntrada.getEmail() );
        }

        return clienteEntity1;
    }

    @Override
    public List<ClienteSaida> mapToSaidaList(List<ClienteEntity> clienteEntityList) {
        if ( clienteEntityList == null ) {
            return null;
        }

        List<ClienteSaida> list = new ArrayList<ClienteSaida>( clienteEntityList.size() );
        for ( ClienteEntity clienteEntity : clienteEntityList ) {
            list.add( mapToSaida( clienteEntity ) );
        }

        return list;
    }
}
