package br.com.fiap.biooffset.dto;

import br.com.fiap.biooffset.model.Biomas;
import br.com.fiap.biooffset.model.Projeto;
import br.com.fiap.biooffset.model.Status;

public record ProjetoExibicaoDTO(
        Long id,
        Long empresaDesenvolvedora,
        String nome,
        String descricao,
        Biomas bioma,
        Double latitude,
        Double longitude,
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
