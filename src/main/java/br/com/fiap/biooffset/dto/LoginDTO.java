package br.com.fiap.biooffset.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
// import org.hibernate.validator.constraints.br.CNPJ;

public record LoginDTO(
        @NotBlank(message = "É obrigatório informar o CNPJ.")
        // @CNPJ(message = "Informe um CNPj válido.")
        String cnpj,

        @NotBlank(message = "Por favor, digite a senha.")
        @Size(min = 8, max = 20, message = "A senha deve ter no mínimo 8 caracteres e no máximo 20.")
        String senha
) {
}
