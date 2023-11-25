package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.dtos.TipoUsuarioDto;
import com.botquest.BotQuestAPI.dtos.UsuarioDto;
import com.botquest.BotQuestAPI.models.TipoUsuarioModel;
import com.botquest.BotQuestAPI.models.UsuarioModel;
import com.botquest.BotQuestAPI.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarUsuario (@PathVariable(value = "idUsuario")UUID id){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse usuário não existe");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping
    public  ResponseEntity<Object> cadastrarUsuario (@RequestBody @Valid UsuarioDto usuarioDto){
        UsuarioModel usuario = new UsuarioModel();

        BeanUtils.copyProperties(usuarioDto, usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PutMapping(value="/{idUsuario}")
    public ResponseEntity<Object> editarUsuario (@PathVariable(value = "idUsuario") UUID id, @ModelAttribute @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if(usuarioBuscado.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse usuário não existe");
        }

        UsuarioModel usuario = usuarioBuscado.get();

        BeanUtils.copyProperties(usuarioDto, usuario);

        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deletarUsuario (@PathVariable(value = "idUsuario") UUID id){
        Optional<UsuarioModel> usuarioBuscado = (usuarioRepository.findById(id));

        if(usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        usuarioRepository.delete(usuarioBuscado.get());

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado!");
    }

}
