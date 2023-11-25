package com.botquest.BotQuestAPI.dtos;

import jakarta.validation.constraints.NotBlank;

public record TipoUsuarioDto(@NotBlank String nome) {
}
