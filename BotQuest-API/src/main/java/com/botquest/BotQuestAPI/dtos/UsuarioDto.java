package com.botquest.BotQuestAPI.dtos;

import com.botquest.BotQuestAPI.models.TipoUsuarioModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record UsuarioDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull int vw_id,
        @NotNull Date data_nascimento,
        @NotNull UUID id_setor,
        @NotNull TipoUsuarioModel tipo_usuario
        ) {
}
