package com.botquest.BotQuestAPI.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetorDto(@NotBlank int cod_setor,
                       @NotBlank String nome) {
}
