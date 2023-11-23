package com.botquest.BotQuestAPI.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UsuarioDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotBlank int vwId,
        @NotBlank Date dataNascimento
        ) {
}
