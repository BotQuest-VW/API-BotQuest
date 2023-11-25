package com.botquest.BotQuestAPI.models;// Importa as anotações necessárias do JPA e Lombok
import com.botquest.BotQuestAPI.models.SetorModel;
import com.botquest.BotQuestAPI.models.TipoUsuarioModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

// Marca a classe como uma entidade JPA
@Getter
@Setter
@Entity
// Especifica o nome da tabela no banco de dados
@Table(name = "tb_usuario")
public class UsuarioModel implements Serializable {
    @Serial
    private static final long serialVersion = 1L;
    // Define a chave primária da entidade
    @Id
    // Configura a estratégia de geração automática do ID
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Especifica o nome da coluna no banco de dados
    @Column(name = "id", nullable = false)
    private UUID id;

    // Atributos da entidade
    String nome;
    String email;
    String senha;
    int vwId;
    Date dataNascimento;

    // Relacionamento de um para um com a entidade TipoUsuarioModel
    @OneToOne
    @JoinColumn(name="id_tipoUsuario", referencedColumnName = "id")
    private TipoUsuarioModel tipoUsuarioModel;

    // Relacionamento de um para um com a entidade SetorModel
    @OneToOne
    @JoinColumn(name="id_setor", referencedColumnName = "id")
    private SetorModel setorModel;
}
