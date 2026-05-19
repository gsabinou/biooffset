package br.com.fiap.biooffset.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreditoCadastroDTO(
        Long id,

        @NotNull(message = "O projeto deve ser informado.")
        Long projetoId,

        @NotNull(message = "O ano de captura é obrigatório")
        int ano,

        @NotBlank(message = "O código é obrigatório.")
        String codigo,

        @NotNull(message = "Por favor, informe a quantidade atual.")
        Double quantidadeTotal,

        @NotNull(message = "Informe a quantidade disponível.")
        Double quantidadeDisponivel,

        @NotNull(message = "Digite o valor unitário.")
        Double valorUnitario
) {
}
