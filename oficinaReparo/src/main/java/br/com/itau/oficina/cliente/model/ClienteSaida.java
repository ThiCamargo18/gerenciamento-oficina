package br.com.itau.oficina.cliente.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteSaida {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Integer telefone;
}
