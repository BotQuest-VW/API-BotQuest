package com.botquest.BotQuestAPI.repositories;

import com.botquest.BotQuestAPI.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
}