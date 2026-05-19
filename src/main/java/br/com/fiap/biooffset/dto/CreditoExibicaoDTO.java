package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.CreditoCarbono;

public record CreditoExibicaoDTO(
        Long id,
        Long projetoId,
        int ano,
        String codigo,
        Double quantidadeTotal,
        Double quantidadeDisponivel,
        Double valorUnitario
) {
    public CreditoExibicaoDTO(CreditoCarbono creditoCarbono) {
        this (
                creditoCarbono.getId(),
                creditoCarbono.getProjetoId().getId(),
                creditoCarbono.getAno(),
                creditoCarbono.getCodigo(),
                creditoCarbono.getQuantidadeTotal(),
                creditoCarbono.getQuantidadeDisponivel(),
                creditoCarbono.getValorUnitario()
        );
    }
}
