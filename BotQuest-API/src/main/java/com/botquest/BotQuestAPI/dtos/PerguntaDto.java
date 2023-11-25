package com.botquest.BotQuestAPI.dtos;

import com.botquest.BotQuestAPI.models.SetorModel;

import java.util.UUID;

public record PerguntaDto(String titulo,
                          String pergunta,
                          UUID id_setor) {
}
