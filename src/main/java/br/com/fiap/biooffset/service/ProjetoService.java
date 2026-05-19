package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.ProjetoCadastroDTO;
import br.com.fiap.biooffset.dto.ProjetoExibicaoDTO;
import br.com.fiap.biooffset.exceptions.EmpresaNotFoundException;
import br.com.fiap.biooffset.exceptions.ProjetoNotFoundException;
import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.model.Projeto;
import br.com.fiap.biooffset.repository.EmpresaRepository;
import br.com.fiap.biooffset.repository.ProjetoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public ProjetoExibicaoDTO criarProjeto(ProjetoCadastroDTO projetoDTO) {

        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoDTO, projeto);

        Optional<Empresa> empresa = empresaRepository.findById(projetoDTO.empresaDesenvolvedora());
        if (empresa.isPresent()) {
            projeto.setEmpresaDesenvolvedora(empresa.get());
            Projeto projetoSalvo = projetoRepository.save(projeto);
            return new ProjetoExibicaoDTO(projetoSalvo);
        } else {
            throw new EmpresaNotFoundException("Empresa desenvolvedora não encontrada.");
        }
    }

    public Page<ProjetoExibicaoDTO> listarTodos(Pageable paginacao) {
        return projetoRepository
                .findAll(paginacao)
                .map(ProjetoExibicaoDTO::new);
    }

    public ProjetoExibicaoDTO buscarPorNome(String nome) {
        Optional<Projeto> projetoOptional = projetoRepository.buscarPorNome(nome);

        if (projetoOptional.isPresent()) {
            return new ProjetoExibicaoDTO(projetoOptional.get());
        } else {
            throw new ProjetoNotFoundException("Projeto não encontrado.");
        }
    }

    public ProjetoExibicaoDTO atualizar(ProjetoCadastroDTO projetoDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoDTO.id());

        if (projetoOptional.isPresent()) {
            Projeto projeto = new Projeto();
            BeanUtils.copyProperties(projetoDTO, projeto);

            Optional<Empresa> empresa = empresaRepository.findById(projetoDTO.empresaDesenvolvedora());
            if (empresa.isPresent()) {
                projeto.setEmpresaDesenvolvedora(empresa.get());
                Projeto projetoSalvo = projetoRepository.save(projeto);
                return new ProjetoExibicaoDTO(projetoSalvo);
            } else {
                throw new EmpresaNotFoundException("Empresa desenvolvedora não encontrada.");
            }
        } else {
            throw new ProjetoNotFoundException("Projeto não encontrado.");
        }
    }

    public void deletar(Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);

        if (projetoOptional.isPresent()) {
            projetoRepository.delete(projetoOptional.get());
        } else {
            throw new ProjetoNotFoundException("Projeto não existe.");
        }
    }
}
