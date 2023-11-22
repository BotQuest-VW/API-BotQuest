package com.botquest.BotQuestAPI.repositories;

import com.botquest.BotQuestAPI.models.TipoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioModel, UUID> {
}