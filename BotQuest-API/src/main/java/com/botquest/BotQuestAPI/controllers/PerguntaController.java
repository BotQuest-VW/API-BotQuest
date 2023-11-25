package com.botquest.BotQuestAPI.controllers;

import com.botquest.BotQuestAPI.dtos.PerguntaDto;
import com.botquest.BotQuestAPI.dtos.SetorDto;
import com.botquest.BotQuestAPI.models.PerguntaModel;
import com.botquest.BotQuestAPI.models.SetorModel;
import com.botquest.BotQuestAPI.repositories.PerguntaRepository;
import com.botquest.BotQuestAPI.repositories.SetorRepository;
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
@RequestMapping(value = "/pergunta", produces = {"application/json"})
public class PerguntaController {
    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public ResponseEntity<List<PerguntaModel>> listarPerguntas() {
        return ResponseEntity.status(HttpStatus.OK).body(perguntaRepository.findAll());
    }

    @GetMapping("/{idPergunta}")
    public ResponseEntity<Object> buscarPergunta(@PathVariable(value = "idPergunta") UUID id) {
        Optional<PerguntaModel> perguntaBuscada = perguntaRepository.findById(id);

        if (perguntaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pergunta não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(perguntaBuscada.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPergunta(@RequestBody @Valid PerguntaDto perguntaDto) {
        PerguntaModel pergunta = new PerguntaModel();

        BeanUtils.copyProperties(perguntaDto, pergunta);

        var setor = setorRepository.findById(perguntaDto.id_setor());

        if (setor.isPresent()) {
            pergunta.setSetorModel(setor.get());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id_setor não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaRepository.save(pergunta));
    }

    @PutMapping("/{idPergunta}")
    public ResponseEntity<Object> editarPergunta(@PathVariable(value = "idPergunta") UUID id, @RequestBody @Valid PerguntaDto perguntaDto) {
        Optional<PerguntaModel> perguntaBuscada = perguntaRepository.findById(id);

        if (perguntaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pergunta não encontrada");
        }

        PerguntaModel pergunta = perguntaBuscada.get();

        if (perguntaDto.id_setor() != null) {
            var setorOptional = setorRepository.findById(perguntaDto.id_setor());

            if (setorOptional.isPresent()) {
                pergunta.setSetorModel(setorOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id_setor não encontrado");
            }
        }

        BeanUtils.copyProperties(perguntaDto, pergunta);

        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaRepository.save(pergunta));
    }

    @DeleteMapping("/{idPergunta}")
    public ResponseEntity<Object> deletarPergunta(@PathVariable(value = "idPergunta") UUID id) {
        Optional<PerguntaModel> perguntaBuscada = perguntaRepository.findById(id);

        if (perguntaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pergunta não encontrada");
        }

        perguntaRepository.delete(perguntaBuscada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pergunta deletada!");
    }


}
