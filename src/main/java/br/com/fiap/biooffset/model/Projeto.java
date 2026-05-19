package br.com.fiap.biooffset.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bio_projetos_ambientais")
public class Projeto {

    @Id
    @Column(name = "id_projeto")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="SEQ_PROJETOS"
    )
    @SequenceGenerator(
            name = "SEQ_PROJETOS",
            sequenceName = "SEQ_PROJETOS",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "empresa_desenvolvedora_id")
    private Long empresaDesenvolvedora;

    @Column(name = "ds_nome")
    private String nome;

    @Column(name = "ds_projeto")
    private String descricao;

    @Column(name = "nm_bioma")
    @Enumerated(EnumType.STRING)
    private Biomas bioma;

    @Column(name = "nr_latitude")
    private Double latitude;

    @Column(name = "nr_longitude")
    private Double longitude;

    @Column(name = "st_status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
