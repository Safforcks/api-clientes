package br.com.software.api_clientes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Cliente {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Integer ativo;


}
