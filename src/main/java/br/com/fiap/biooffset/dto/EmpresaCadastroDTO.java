package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.EmpresaRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaCadastroDTO(
        Long id,

        @NotBlank(message = "O nome jurídico é obrigatório.")
        String razaoSocial,

        @NotBlank(message = "É obrigatório informar o CNPJ.")
        @Size(min = 14, max = 14, message = "Por favor, informe um CNPJ válido.")
        String cnpj,

        @NotBlank(message = "Escolha uma senha.")
        @Size(min = 8, max = 20, message = "A senha deve conter entre 8 a 20 caracteres!")
        String senha,

        @NotBlank(message = "Por favor, informe se a sua empresa é compradora ou desenvolvedora.")
        EmpresaRole role

) {
}
