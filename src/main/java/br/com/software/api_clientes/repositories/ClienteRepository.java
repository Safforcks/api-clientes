package br.com.software.api_clientes.repositories;

import br.com.software.api_clientes.entities.Cliente;
import br.com.software.api_clientes.factories.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public boolean atualizar(Cliente cliente) throws Exception {

        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {

            var sql = """
                    update clientes set nome=?, email=?, cpf=?, telefone=?
                    where id=? and ativo = 1
                    """;

            var statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getTelefone());
            statement.setObject(5, cliente.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean excluir(UUID id) throws Exception {

        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {

            var sql = "update clientes set ativo = 0 where id=? and ativo = 1";

            var statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Cliente> obterTodos() throws Exception {

        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {

            var sql = """
                    select id, nome, email, cpf, telefone
                    from clientes where ativo = 1
                    """;

            var statement = connection.prepareStatement(sql);
            var result = statement.executeQuery();

            var lista = new ArrayList<Cliente>();

            while (result.next()) {
                var cliente = new Cliente();

                cliente.setId(UUID.fromString(result.getString("id")));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setCpf(result.getString("cpf"));

                lista.add(cliente);
            }

            return lista;
        }
    }

    public Cliente obterPorid(UUID id) throws Exception {

        var factory = new ConnectionFactory();

        try (var connection = factory.createConnection()) {

            var sql = """
                    select id, nome, email, cpf, telefone
                    from clientes where id=? and ativo = 1
                    """;

            var statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            var result = statement.executeQuery();


            if (result.next()) {
                var cliente = new Cliente();

                cliente.setId(UUID.fromString(result.getString("id")));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setTelefone(result.getString("telefone"));

                return cliente;
            } else {
                return null;
            }
        }
    }
}
