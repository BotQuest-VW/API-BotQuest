package com.botquest.BotQuestAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    String nome;

    String email;

    String senha;

    int vwId;

    Date dataNascimento;

    @OneToOne
    @JoinColumn(name="id_tipoUsuario", referencedColumnName = "id")
    private TipoUsuarioModel tipoUsuarioModel;

    @OneToOne
    @JoinColumn(name="id_setor", referencedColumnName = "id")
    private SetorModel setorModel;

}