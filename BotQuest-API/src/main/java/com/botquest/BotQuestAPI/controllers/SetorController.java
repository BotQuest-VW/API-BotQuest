package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.models.SetorModel;
import com.botquest.BotQuestAPI.repositories.SetorRepository;
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
}
