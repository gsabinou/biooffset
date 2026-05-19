package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.CreditoCadastroDTO;
import br.com.fiap.biooffset.dto.CreditoExibicaoDTO;
import br.com.fiap.biooffset.exceptions.CreditoNotFoundException;
import br.com.fiap.biooffset.exceptions.ProjetoNotFoundException;
import br.com.fiap.biooffset.model.CreditoCarbono;
import br.com.fiap.biooffset.model.Projeto;
import br.com.fiap.biooffset.repository.CreditoCarbonoRepository;
import br.com.fiap.biooffset.repository.ProjetoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditoCarbonoService {

    @Autowired
    private CreditoCarbonoRepository creditoCarbonoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public CreditoExibicaoDTO criarCredito(CreditoCadastroDTO creditoDTO) {

        CreditoCarbono carbono = new CreditoCarbono();
        BeanUtils.copyProperties(creditoDTO, carbono);

        Optional<Projeto> projetoOptional = projetoRepository.findById(creditoDTO.projetoId());

        if (projetoOptional.isPresent()) {
            carbono.setProjetoId(projetoOptional.get());
            CreditoCarbono carbonoSalvo = creditoCarbonoRepository.save(carbono);
            return new CreditoExibicaoDTO(carbonoSalvo);
        } else {
            throw new ProjetoNotFoundException("Projeto dos créditos de carbono não encontrado.");
        }
    }

    public Page<CreditoExibicaoDTO> listarTodos(Pageable paginacao) {
        return creditoCarbonoRepository
                .findAll(paginacao)
                .map(CreditoExibicaoDTO::new);
    }

    public void realizarBaixaDeEstoque(Long id, Double quantidadeComprada) {
        Optional<CreditoCarbono> creditoCarbonoOptional = creditoCarbonoRepository.findById(id);

        if (creditoCarbonoOptional.isPresent()) {
            CreditoCarbono creditoCarbono = creditoCarbonoOptional.get();
            creditoCarbono.setQuantidadeDisponivel(
                    creditoCarbono.getQuantidadeDisponivel() - quantidadeComprada);
        } else {
            throw new CreditoNotFoundException("Crédito de carbono informado não encontrado.");
        }
    }

}
