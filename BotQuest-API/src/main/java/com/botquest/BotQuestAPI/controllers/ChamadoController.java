package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.dtos.ChamadoDto;
import com.botquest.BotQuestAPI.models.ChamadoModel;
import com.botquest.BotQuestAPI.repositories.ChamadoRepository;
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
@RequestMapping(value="/chamado", produces = {"application/json"})

public class ChamadoController {
    @Autowired
    ChamadoRepository chamadoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<ChamadoModel>> listarChamados() {
        return ResponseEntity.status(HttpStatus.OK).body(chamadoRepository.findAll());
    }

    @GetMapping("/{idChamado}")
    public ResponseEntity<Object> buscarChamado(@PathVariable(value = "idChamado") UUID id) {
        Optional<ChamadoModel> chamadoBuscado = chamadoRepository.findById(id);

        if (chamadoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(chamadoBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarChamado(@RequestBody @Valid ChamadoDto chamadoDto) {
        ChamadoModel chamado = new ChamadoModel();

        BeanUtils.copyProperties(chamadoDto, chamado);

        var usuario = usuarioRepository.findById(chamadoDto.id_usuario());

        if (usuario.isPresent()) {
            chamado.setUsuario(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id_usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoRepository.save(chamado));
    }

    @PutMapping("/{idChamado}")
    public ResponseEntity<Object> editarChamado(@PathVariable(value = "idChamado") UUID id, @RequestBody @Valid ChamadoDto chamadoDto) {
        Optional<ChamadoModel> chamadoBuscado = chamadoRepository.findById(id);

        if (chamadoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado");
        }

        ChamadoModel chamado = chamadoBuscado.get();

        if (chamadoDto.id_usuario() != null) {
            var usuarioOptional = usuarioRepository.findById(chamadoDto.id_usuario());

            if (usuarioOptional.isPresent()) {
                chamado.setUsuario(usuarioOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id_usuario não encontrado");
            }
        }

        BeanUtils.copyProperties(chamadoDto, chamado);

        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoRepository.save(chamado));
    }

    @DeleteMapping("/{idChamado}")
    public ResponseEntity<Object> deletarChamado(@PathVariable(value = "idChamado") UUID id) {
        Optional<ChamadoModel> chamadoBuscado = chamadoRepository.findById(id);

        if (chamadoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado");
        }

        chamadoRepository.delete(chamadoBuscado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Chamado deletado!");
    }

}
