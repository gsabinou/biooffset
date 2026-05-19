package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.ProjetoCadastroDTO;
import br.com.fiap.biooffset.dto.ProjetoExibicaoDTO;
import br.com.fiap.biooffset.model.Projeto;
import br.com.fiap.biooffset.repository.ProjetoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public ProjetoExibicaoDTO criarProjeto(ProjetoCadastroDTO projetoDTO) {

        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoDTO, projeto);

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return new ProjetoExibicaoDTO(projetoSalvo);
    }

}
