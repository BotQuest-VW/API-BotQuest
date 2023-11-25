package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.dtos.TipoUsuarioDto;
import com.botquest.BotQuestAPI.models.SetorModel;
import com.botquest.BotQuestAPI.models.TipoUsuarioModel;
import com.botquest.BotQuestAPI.repositories.TipoUsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Define o controlador para a entidade TipoUsuarioModel
@RestController
@RequestMapping(value="/tipousuario", produces = {"application/json"})
public class TipoUsuarioController {
    // Injeta a dependência do repositório TipoUsuarioRepository
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    // Endpoint para listar todos os tipos de usuários
    @GetMapping
    public ResponseEntity<List<TipoUsuarioModel>> listarTipos() {
        // Recupera a lista de todos os tipos de usuários no banco de dados
        List<TipoUsuarioModel> tipos = tipoUsuarioRepository.findAll();
        // Retorna a lista de tipos de usuários com o código de status OK
        return ResponseEntity.status(HttpStatus.OK).body(tipos);
    }

    // Endpoint para buscar um tipo de usuário por ID
    @GetMapping("/{idTipoUsuario}")
    public ResponseEntity<Object> buscarTipoUsuario(@PathVariable(value="idTipoUsuario")UUID id){
        // Busca o tipo de usuário no banco de dados pelo ID fornecido
        Optional<TipoUsuarioModel> tipoBuscado = tipoUsuarioRepository.findById(id);

        // Verifica se o tipo de usuário foi encontrado
        if(tipoBuscado.isEmpty()){
            // Retorna uma resposta com código de status NOT_FOUND se o tipo de usuário não foi encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse tipo de usuário não existe");
        }

        // Retorna o tipo de usuário encontrado com código de status OK
        return  ResponseEntity.status(HttpStatus.OK).body(tipoBuscado.get());
    }

    // Endpoint para cadastrar um novo tipo de usuário
    @PostMapping
    public  ResponseEntity<Object> cadastrarTipo (@RequestBody @Valid TipoUsuarioDto tipoUsuarioDto){
        // Cria uma nova instância de TipoUsuarioModel
        TipoUsuarioModel tipo = new TipoUsuarioModel();

        // Copia as propriedades do DTO para o modelo
        BeanUtils.copyProperties(tipoUsuarioDto, tipo);

        // Salva o tipo de usuário no banco de dados e retorna com código de status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipo));
    }

    // Endpoint para editar um tipo de usuário por ID
    @PutMapping(value="/{idTipoUsuario}")
    public ResponseEntity<Object> editarTipo (@PathVariable(value = "idTipoUsuario") UUID id, @ModelAttribute @Valid TipoUsuarioDto tipoUsuarioDto){
        // Busca o tipo de usuário no banco de dados pelo ID fornecido
        Optional<TipoUsuarioModel> tipoBuscado = tipoUsuarioRepository.findById(id);

        // Verifica se o tipo de usuário foi encontrado
        if(tipoBuscado.isEmpty()){
            // Retorna uma resposta com código de status NOT_FOUND se o tipo de usuário não foi encontrado
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse tipo de usuário não existe");
        }

        // Obtém o tipo de usuário encontrado
        TipoUsuarioModel tipo = tipoBuscado.get();

        // Copia as propriedades do DTO para o modelo
        BeanUtils.copyProperties(tipoUsuarioDto, tipo);

        // Salva o tipo de usuário atualizado no banco de dados e retorna com código de status CREATED
        return  ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipo));
    }

    // Endpoint para deletar um tipo de usuário por ID
    @DeleteMapping("/{idTipoUsuario}")
    public ResponseEntity<Object> deletarTipo (@PathVariable(value = "idTipoUsuario") UUID id){
        // Busca o tipo de usuário no banco de dados pelo ID fornecido
        Optional<TipoUsuarioModel> tipoBuscado = (tipoUsuarioRepository.findById(id));

        // Verifica se o tipo de usuário foi encontrado
        if(tipoBuscado.isEmpty()){
            // Retorna uma resposta com código de status NOT_FOUND se o tipo de usuário não foi encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de usuário não encontrado");
        }

        // Remove o tipo de usuário do banco de dados e retorna com código de status NO_CONTENT
        tipoUsuarioRepository.delete(tipoBuscado.get());

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tipo de usuário deletado!");
    }
}