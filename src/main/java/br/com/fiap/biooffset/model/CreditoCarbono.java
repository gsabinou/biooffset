package br.com.fiap.biooffset.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bio_creditos_carbono")
public class CreditoCarbono {

    @Id
    @Column(name = "id_credito")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="SEQ_CREDITOS"
    )
    @SequenceGenerator(
            name = "SEQ_CREDITOS",
            sequenceName = "SEQ_CREDITOS",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projeto")
    private Projeto projetoId;

    @Column(name = "ano_captura")
    private int ano;

    @Column(name = "cod_homologacao")
    private String codigo;

    @Column(name = "qt_total")
    private Double quantidadeTotal;

    @Column(name = "qt_disponivel")
    private Double quantidadeDisponivel;

    @Column(name = "vl_unitario")
    private Double valorUnitario;
}
