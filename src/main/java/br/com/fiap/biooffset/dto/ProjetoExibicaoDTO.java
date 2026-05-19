package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Biomas;
import br.com.fiap.biooffset.model.Projeto;
import br.com.fiap.biooffset.model.Status;

public record ProjetoExibicaoDTO(
        Long id,
        String empresaDesenvolvedora,
        String nome,
        String descricao,
        Biomas bioma,
        Float latitude,
        Float longitude,
        Status status
) {
    public ProjetoExibicaoDTO(Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getEmpresaDesenvolvedora(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getBioma(),
                projeto.getLatitude(),
                projeto.getLongitude(),
                projeto.getStatus()
        );
    }
}
