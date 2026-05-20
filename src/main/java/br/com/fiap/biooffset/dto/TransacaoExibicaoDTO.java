package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Transacao;

import java.time.LocalDateTime;

public record TransacaoExibicaoDTO(
        Long id,
        Long empresaCompradoraId,
        Long creditoId,
        Double quantidadeComprada,
        Double valorTotal,
        LocalDateTime dataTransacao
) {

    public TransacaoExibicaoDTO(Transacao transacao) {
        this(
                transacao.getId(),
                transacao.getEmpresaCompradoraId().getId(),
                transacao.getCreditoId().getId(),
                transacao.getQuantidadeComprada(),
                transacao.getValorTotal(),
                transacao.getDataTransacao()
        );

    }
}
