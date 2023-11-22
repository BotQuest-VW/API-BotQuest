package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.dtos.SetorDto;
import com.botquest.BotQuestAPI.models.SetorModel;
import com.botquest.BotQuestAPI.repositories.SetorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/setor", produces = {"application/json"})
public class SetorController {
    @Autowired
    SetorRepository setorRepository;

    @GetMapping
    public ResponseEntity<Object> buscarSetor(@PathVariable(value = "idSetor")UUID id){
        Optional<SetorModel> setorBuscado = setorRepository.findById(id);

        if (setorBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este setor não existe");
        }

        return  ResponseEntity.status(HttpStatus.OK).body(setorBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarSetor(@RequestBody @Valid SetorDto setorDto){
        SetorModel setor = new SetorModel();

        BeanUtils.copyProperties(setorDto, setor);

        return  ResponseEntity.status(HttpStatus.CREATED).body(setorRepository.save(setor));
    }

    @PutMapping(value = "/{idSetor}")
    public ResponseEntity<Object> editarSetor (@PathVariable(value = "idSetor") UUID id, @ModelAttribute @Valid SetorDto setorDto){
        Optional<SetorModel> setorBuscado = setorRepository.findById(id);

        if(setorBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado");
        }

        SetorModel setor = setorBuscado.get();

        BeanUtils.copyProperties(setorDto, setor);

        return  ResponseEntity.status(HttpStatus.CREATED).body(setorRepository.save(setor));
    }

    @DeleteMapping("/{idSetor}")
    public ResponseEntity<Object> deletarSetor(@PathVariable(value = "idSetor") UUID id){
        Optional<SetorModel> setorBuscado = (setorRepository.findById(id));

        if(setorBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrada");
        }

        setorRepository.delete(setorBuscado.get());

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Setor deletado!");
    }
}
