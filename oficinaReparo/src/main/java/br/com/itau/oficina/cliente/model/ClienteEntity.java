package br.com.itau.oficina.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    String nome;

    @NotNull(message = "CPF não pode estar em branco")
    @Column(name = "cpf")
    String cpf;

    @NotNull(message = "Email não pode estar em branco")
    @Column(name = "email")
    String email;

    @Column(name = "telefone")
    Integer telefone;
}
