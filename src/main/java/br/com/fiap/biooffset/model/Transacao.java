package br.com.fiap.biooffset.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bio_transacoes_compra")
public class Transacao {

    @Id
    @Column(name = "id_transacao")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="SEQ_TRANSACOES"
    )
    @SequenceGenerator(
            name = "SEQ_TRANSACOES",
            sequenceName = "SEQ_TRANSACOES",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa_compradora")
    private Empresa empresaCompradoraId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_credito")
    private CreditoCarbono creditoId;

    @Column(name = "qt_comprada")
    private Double quantidadeComprada;

    @Column(name = "vl_total")
    private Double valorTotal;

    @Column(name = "dt_transacao")
    private LocalDateTime dataTransacao;

    @PrePersist
    public void prePersist() {
        this.dataTransacao = LocalDateTime.now();
    }
}
