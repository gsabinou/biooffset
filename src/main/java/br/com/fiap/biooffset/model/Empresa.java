package br.com.fiap.biooffset.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bio_empresas")
public class Empresa {

    @Id
    @Column(name = "id_empresa")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="SEQ_EMPRESAS"
    )
    @SequenceGenerator(
            name = "SEQ_EMPRESAS",
            sequenceName = "SEQ_EMPRESAS",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "raz_social")
    private String razaoSocial;

    @Column(name = "nr_cnpj")
    private String cnpj;

    @Column(name = "ds_senha")
    private String senha;

    @Column(name = "tip_empresa")
    @Enumerated(EnumType.STRING)
    private EmpresaRole role;
}

