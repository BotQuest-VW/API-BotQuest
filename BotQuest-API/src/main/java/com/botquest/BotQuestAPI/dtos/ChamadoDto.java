package com.botquest.BotQuestAPI.dtos;

import java.util.Date;
import java.util.UUID;

public record ChamadoDto(String descricao,
                         boolean situacao,
                         Date data_chamado,
                         UUID id_usuario) {
}
