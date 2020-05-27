package br.com.itau.oficina.cliente.facade;

import br.com.itau.oficina.cliente.mapper.ClienteMapper;
import br.com.itau.oficina.cliente.model.ClienteEntity;
import br.com.itau.oficina.cliente.model.ClienteEntrada;
import br.com.itau.oficina.cliente.model.ClienteSaida;
import br.com.itau.oficina.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteFacade {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteSaida salvar(ClienteEntrada clienteEntrada) throws Exception {
            ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(clienteEntrada);
            List<ClienteEntity> listaCliente = clienteRepository.findAll();

            for(ClienteEntity cliente : listaCliente){
                if(cliente.getCpf().equals(clienteEntrada.getCpf())){
                    throw new Exception("Ja possui um cliente com o mesmo CPF cadastrado! Selecione outro.");
                }
                if(cliente.getEmail().equals(clienteEntrada.getEmail())){
                    throw new Exception("Ja possui um cliente com o mesmo e-mail cadastrado! Selecione outro.");
                }
            }

            clienteEntity = clienteRepository.save(clienteEntity);

            ClienteSaida clienteSaida = ClienteMapper.INSTANCE.mapToSaida(clienteEntity);

            return clienteSaida;
    }

    public ClienteSaida obter(String cpf) throws Exception {
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findByCpf(cpf);

        ClienteEntity clienteEntity;

        if(!optionalClienteEntity.isPresent()){
            throw new Exception("Cliente nao encontrado!");
        }else{
            clienteEntity = optionalClienteEntity.get();
            return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
        }
    }

    public ClienteSaida atualizar(String cpf, ClienteEntrada clienteEntrada) throws Exception {
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findByCpf(cpf);

        ClienteEntity clienteEntity;

        if(!optionalClienteEntity.isPresent()){
            throw new Exception("Cliente nao encontrado!");
        }


        clienteEntity = optionalClienteEntity.get();

        clienteEntity = ClienteMapper.INSTANCE.mapToEntityAtualizar(clienteEntity,clienteEntrada);

        clienteRepository.save(clienteEntity);

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public List<ClienteSaida> listar() {
        List<ClienteEntity> clienteEntityList = clienteRepository.findAll();

        return ClienteMapper.INSTANCE.mapToSaidaList(clienteEntityList);
    }

    public ClienteEntity findByCpf(String cpf) throws Exception {
        Optional<ClienteEntity> byCpf = clienteRepository.findByCpf(cpf);

        if(!byCpf.isPresent()){
            throw new Exception("Cliente não encontrado!");
        }

        ClienteEntity clienteEntity = byCpf.get();

        return clienteEntity;
    }
}
