package br.com.software.api_clientes.controllers;

import br.com.software.api_clientes.entities.Cliente;
import br.com.software.api_clientes.repositories.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @PostMapping
    public ResponseEntity<String> post (@RequestBody Map<String, String> dados) {

        var cliente = new Cliente();

        cliente.setId(UUID.randomUUID());
        cliente.setNome(dados.get("nome"));
        cliente.setEmail(dados.get("email"));
        cliente.setCpf(dados.get("cpf"));
        cliente.setTelefone(dados.get("telefone"));

        try {

            var clienteRepository = new ClienteRepository();

            clienteRepository.inserir(cliente);

            return ResponseEntity.ok("Cliente cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@RequestBody Map<String, String> dados) {

        var cliente = new Cliente();

        cliente.setNome(dados.get("nome"));
        cliente.setEmail(dados.get("email"));
        cliente.setCpf(dados.get("cpf"));
        cliente.setTelefone(dados.get("telefone"));

        try {

            var clienteRepository = new ClienteRepository();

            if (clienteRepository.atualizar(cliente)) {
                return ResponseEntity.ok("Cliente atualizado com sucesso.");
            } else {
                return ResponseEntity.status(404).body("Cliente não encontrado para edição.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PatchMapping UUID id) {

        try {

            var clienteRepository = new ClienteRepository();

            if (clienteRepository.excluir(id)) {
                return ResponseEntity.ok("Cliente excluido com sucesso");
            } else {
                return ResponseEntity.status(404).body("Cliente não encontrado para exclusão.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
