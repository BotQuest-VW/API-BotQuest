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

@RestController
@RequestMapping(value="/tipousuario", produces = {"application/json"})
public class TipoUsuarioController {
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping
    public ResponseEntity<List<TipoUsuarioModel>> listarTipos() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findAll());
    }
    @GetMapping("/{idTipoUsuario}")
    public ResponseEntity<Object> buscarTipoUsuario(@PathVariable(value="idTipoUsuario")UUID id){
        Optional<TipoUsuarioModel> tipoBuscado = tipoUsuarioRepository.findById(id);

        if(tipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse tipo de usuário não existe");
        }

        return  ResponseEntity.status(HttpStatus.OK).body(tipoBuscado.get());
    }

    @PostMapping
    public  ResponseEntity<Object> cadastrarTipo (@RequestBody @Valid TipoUsuarioDto tipoUsuarioDto){
        TipoUsuarioModel tipo = new TipoUsuarioModel();

        BeanUtils.copyProperties(tipoUsuarioDto, tipo);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipo));
    }

    @PutMapping(value="/{idTipoUsuario}")
    public ResponseEntity<Object> editarTipo (@PathVariable(value = "idTipoUsuario") UUID id, @ModelAttribute @Valid TipoUsuarioDto tipoUsuarioDto){
        Optional<TipoUsuarioModel> tipoBuscado = tipoUsuarioRepository.findById(id);

        if(tipoBuscado.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse tipo de usuário não existe");
        }

        TipoUsuarioModel tipo = tipoBuscado.get();

        BeanUtils.copyProperties(tipoUsuarioDto, tipo);

        return  ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipo));
    }

    @DeleteMapping("/{idTipoUsuario}")
    public ResponseEntity<Object> deletarTipo (@PathVariable(value = "idTipoUsuario") UUID id){
        Optional<TipoUsuarioModel> tipoBuscado = (tipoUsuarioRepository.findById(id));

        if(tipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de usuário não encontrado");
        }

        tipoUsuarioRepository.delete(tipoBuscado.get());

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tipo de usuário deletado!");
    }
}
