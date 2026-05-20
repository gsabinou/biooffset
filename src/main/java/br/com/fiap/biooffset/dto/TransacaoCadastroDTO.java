package br.com.fiap.biooffset.dto;

import jakarta.validation.constraints.NotNull;

public record TransacaoCadastroDTO(

        Long id,

        @NotNull(message = "Por favor, informe o ID da empresa que irá realizar a transação.")
        Long empresaCompradoraId,

        @NotNull(message = "O ID dos créditos de carbono é obrigatório.")
        Long creditoId,

        @NotNull(message = "Informe a quantidade que deseja comprar, por favor.")
        Double quantidadeComprada

) {
}
