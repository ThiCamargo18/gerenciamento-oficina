package br.com.itau.oficina.peca.mapper;

import br.com.itau.oficina.peca.model.PecaEntity;
import br.com.itau.oficina.peca.model.PecaEntrada;
import br.com.itau.oficina.peca.model.PecaSaida;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-27T18:57:48-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class PecaMapperImpl implements PecaMapper {

    @Override
    public PecaEntity mapToEntity(PecaEntrada pecaEntrada) {
        if ( pecaEntrada == null ) {
            return null;
        }

        PecaEntity pecaEntity = new PecaEntity();

        pecaEntity.setValor( pecaEntrada.getValor() );
        pecaEntity.setNome( pecaEntrada.getNome() );

        return pecaEntity;
    }

    @Override
    public PecaSaida mapToSaida(PecaEntity pecaEntity) {
        if ( pecaEntity == null ) {
            return null;
        }

        PecaSaida pecaSaida = new PecaSaida();

        pecaSaida.setId( pecaEntity.getId() );
        pecaSaida.setValor( pecaEntity.getValor() );
        pecaSaida.setNome( pecaEntity.getNome() );

        return pecaSaida;
    }

    @Override
    public List<PecaSaida> mapToSaidaList(List<PecaEntity> listaEntity) {
        if ( listaEntity == null ) {
            return null;
        }

        List<PecaSaida> list = new ArrayList<PecaSaida>( listaEntity.size() );
        for ( PecaEntity pecaEntity : listaEntity ) {
            list.add( mapToSaida( pecaEntity ) );
        }

        return list;
    }
}
