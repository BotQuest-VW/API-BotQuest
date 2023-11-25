package com.botquest.BotQuestAPI.dtos;

import com.botquest.BotQuestAPI.models.SetorModel;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PerguntaDto(@NotBlank  String titulo,
                          @NotBlank  String pergunta,
                          @NotBlank UUID id_setor) {
}
