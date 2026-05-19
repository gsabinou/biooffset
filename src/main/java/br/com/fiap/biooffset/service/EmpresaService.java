package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.exceptions.EmpresaNotFoundException;
import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaExibicaoDTO criarEmpresa(EmpresaCadastroDTO empresaCadastroDTO) {

        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaCadastroDTO, empresa);

        Empresa empresaSalva = empresaRepository.save(empresa);
        return new EmpresaExibicaoDTO(empresa);
    }

    public EmpresaExibicaoDTO buscarPorRazaoSocial(String razaoSocial) {
        Optional<Empresa> empresaOptional = empresaRepository.findByRazaoSocial(razaoSocial);

        if (empresaOptional.isPresent()) {
            return new EmpresaExibicaoDTO(empresaOptional.get());
        } else {
            throw new EmpresaNotFoundException("Usuário não encontrado.");
        }
    }

    public List<EmpresaExibicaoDTO> listarTodas() {
        return empresaRepository
                .findAll()
                .stream()
                .map(EmpresaExibicaoDTO::new)
                .toList();
    }



}
