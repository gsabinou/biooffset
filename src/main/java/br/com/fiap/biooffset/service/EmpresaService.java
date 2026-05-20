package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.dto.ProjetoExibicaoDTO;
import br.com.fiap.biooffset.exceptions.EmpresaNotFoundException;
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
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public EmpresaExibicaoDTO criarEmpresa(EmpresaCadastroDTO empresaCadastroDTO) {

        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaCadastroDTO, empresa);

        Empresa empresaSalva = empresaRepository.save(empresa);
        return new EmpresaExibicaoDTO(empresaSalva);
    }

    public EmpresaExibicaoDTO buscarPorCnpj(String cnpj) {
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(cnpj);

        if (empresaOptional.isPresent()) {
            return new EmpresaExibicaoDTO(empresaOptional.get());
        } else {
            throw new EmpresaNotFoundException("Empresa não encontrada.");
        }
    }

    public Page<EmpresaExibicaoDTO> listarTodas(Pageable paginacao) {
        return empresaRepository
                .findAll(paginacao)
                .map(EmpresaExibicaoDTO::new);
    }

    public EmpresaExibicaoDTO atualizar(EmpresaCadastroDTO empresaCadastroDTO) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(empresaCadastroDTO.id());

        if (empresaOptional.isPresent()) {
            Empresa empresa = new Empresa();
            BeanUtils.copyProperties(empresaCadastroDTO, empresa);
            return new EmpresaExibicaoDTO(empresaRepository.save(empresa));
        } else {
            throw new EmpresaNotFoundException("Empresa não encontrada.");
        }
    }

    public void deletar(String cnpj) {
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(cnpj);

        if (empresaOptional.isPresent()) {
            empresaRepository.delete(empresaOptional.get());
        } else {
            throw new EmpresaNotFoundException("CNPJ não encontrado.");
        }
    }

    public Page<ProjetoExibicaoDTO> listarProjetos(Long id, Pageable paginacao) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            return projetoRepository.findByEmpresaDesenvolvedora_Id(id, paginacao)
                    .map(ProjetoExibicaoDTO::new);

        } else {
            throw new EmpresaNotFoundException("Empresa não encontrada.");
        }
    }

}
