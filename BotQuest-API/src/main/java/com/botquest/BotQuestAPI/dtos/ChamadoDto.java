package com.botquest.BotQuestAPI.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.UUID;

public record ChamadoDto(@NotBlank  String descricao,
                         @NotBlank  boolean situacao,
                         @NotBlank  Date data_chamado,
                         @NotBlank  UUID id_usuario) {
}
