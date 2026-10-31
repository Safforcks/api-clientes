package br.com.software.api_clientes.repositories;

import br.com.software.api_clientes.entities.Cliente;
import br.com.software.api_clientes.factories.ConnectionFactory;

public class ClienteRepository {

    public void inserir(Cliente cliente) throws Exception {

        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()){

            var sql = """
                    insert into clientes (id, nome, email, cpf, telefone) values (?, ?, ?, ?, ?)
                    """;

            var statement = connection.prepareStatement(sql);

            statement.setObject(1, cliente.getId());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getTelefone());
            statement.execute();
        }
    }
}
