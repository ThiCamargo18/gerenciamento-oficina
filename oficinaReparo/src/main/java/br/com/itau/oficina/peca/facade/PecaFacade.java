package br.com.itau.oficina.peca.facade;

import br.com.itau.oficina.peca.mapper.PecaMapper;
import br.com.itau.oficina.peca.model.PecaEntity;
import br.com.itau.oficina.peca.model.PecaEntrada;
import br.com.itau.oficina.peca.model.PecaSaida;
import br.com.itau.oficina.peca.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PecaFacade {
    @Autowired
    PecaRepository pecaRepository;

    public PecaSaida salvar(PecaEntrada pecaEntrada) throws Exception {
        PecaEntity pecaEntity = PecaMapper.INSTANCE.mapToEntity(pecaEntrada);

        pecaEntity = pecaRepository.save(pecaEntity);

        PecaSaida pecaSaida = PecaMapper.INSTANCE.mapToSaida(pecaEntity);

        return pecaSaida;
    }

    public PecaSaida atualizar(Long id, PecaEntrada pecaEntrada) throws Exception {
        Optional<PecaEntity> retornoBanco = pecaRepository.findById(id);

        PecaEntity pecaEntity;

        if(!retornoBanco.isPresent()){
            throw new Exception("Peça não encontrada, por favor cadastre a mesma ou busque novamente!");
        }

        pecaEntity = retornoBanco.get();

        pecaEntity.setNome(pecaEntrada.getNome());
        pecaEntity.setValor(pecaEntrada.getValor());

        pecaEntity = pecaRepository.save(pecaEntity);

        return PecaMapper.INSTANCE.mapToSaida(pecaEntity);
    }

    public List<PecaSaida> listar() {
        List<PecaEntity> listaPecaEntity = pecaRepository.findAll();

        return PecaMapper.INSTANCE.mapToSaidaList(listaPecaEntity);
    }

    public List<PecaEntity> buscarPecaEVerificarExistencia(List<Long> listaPecaLong) throws Exception {
        List<PecaEntity> listaPecaEncontrada = new ArrayList<>();
        for(Long peca : listaPecaLong){
            Optional<PecaEntity> pecaEntityOptional = pecaRepository.findById(peca);

            if(!pecaEntityOptional.isPresent()){
                throw new Exception("Peca nao encontrada! Busque novamente, ou faça o cadastro!");
            }

            listaPecaEncontrada.add(pecaEntityOptional.get());
        }
        return listaPecaEncontrada;
    }

    public PecaSaida obterEspecifica(String nome) {
        PecaEntity pecaEntity = pecaRepository.findByNomeIgnoreCase(nome);

        return PecaMapper.INSTANCE.mapToSaida(pecaEntity);
    }
}
