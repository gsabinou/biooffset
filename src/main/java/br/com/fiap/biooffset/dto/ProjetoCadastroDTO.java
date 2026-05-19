package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Biomas;
import br.com.fiap.biooffset.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjetoCadastroDTO(
        Long id,

        @NotNull(message = "A empresa desenvolvedora deve ser informada.")
        Long empresaDesenvolvedora,

        @NotBlank(message = "O projeto deve ter um nome.")
        String nome,

        @NotBlank(message = "É obrigatório adicionar uma descrição.")
        String descricao,

        @NotNull(message = "O bioma deve ser informado.")
        Biomas bioma,

        @NotNull(message = "A latitude é obrigatória.")
        Double latitude,

        @NotNull(message = "A longitude é obrigatória.")
        Double longitude,

        @NotNull(message = "Por favor, informe o status do projeto.")
        Status status
) {
}
