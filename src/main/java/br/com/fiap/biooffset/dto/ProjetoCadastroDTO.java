package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Biomas;
import br.com.fiap.biooffset.model.Status;
import jakarta.validation.constraints.NotBlank;

public record ProjetoCadastroDTO(
        Long id,

        @NotBlank(message = "A empresa desenvolvedora deve ser informada.")
        String empresaDesenvolvedora,

        @NotBlank(message = "O projeto deve ter um nome.")
        String nome,

        @NotBlank(message = "É obrigatório adicionar uma descrição.")
        String descricao,

        @NotBlank(message = "O bioma deve ser informado.")
        Biomas bioma,

        @NotBlank(message = "A latitude é obrigatória.")
        Float latitude,

        @NotBlank(message = "A longitude é obrigatória.")
        Float longitude,

        @NotBlank(message = "Por favor, informe o status do projeto.")
        Status status
) {
}
