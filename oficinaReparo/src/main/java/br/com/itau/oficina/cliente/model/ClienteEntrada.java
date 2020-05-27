package br.com.itau.oficina.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntrada {
    private String nome;
    private String cpf;
    private String email;
    private Integer telefone;
}
