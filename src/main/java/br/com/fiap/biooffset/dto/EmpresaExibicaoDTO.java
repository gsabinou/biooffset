package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.model.EmpresaRole;

public record EmpresaExibicaoDTO(
        Long id,
        String razaoSocial,
        EmpresaRole role
) {
    public EmpresaExibicaoDTO(Empresa empresa) {
        this(
                empresa.getId(),
                empresa.getRazaoSocial(),
                empresa.getRole()
        );
    }
}
