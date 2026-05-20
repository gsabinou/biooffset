package br.com.fiap.biooffset.service;

import br.com.fiap.biooffset.dto.TransacaoCadastroDTO;
import br.com.fiap.biooffset.dto.TransacaoExibicaoDTO;
import br.com.fiap.biooffset.exceptions.CreditoNotFoundException;
import br.com.fiap.biooffset.exceptions.EmpresaNotFoundException;
import br.com.fiap.biooffset.exceptions.ProjetoNotFoundException;
import br.com.fiap.biooffset.model.CreditoCarbono;
import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.model.Transacao;
import br.com.fiap.biooffset.repository.CreditoCarbonoRepository;
import br.com.fiap.biooffset.repository.EmpresaRepository;
import br.com.fiap.biooffset.repository.TransacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CreditoCarbonoRepository creditoCarbonoRepository;

    public TransacaoExibicaoDTO comprarCreditos(TransacaoCadastroDTO transacaoDTO) {
        Optional<Empresa> empresaOptional = empresaRepository
                .findById(transacaoDTO.empresaCompradoraId());

        if (empresaOptional.isPresent()) {
            Optional<CreditoCarbono> creditoOptional = creditoCarbonoRepository
                    .findById(transacaoDTO.creditoId());

            if(creditoOptional.isPresent()) {

                Transacao transacao = new Transacao();
                BeanUtils.copyProperties(transacaoDTO, transacao);
                transacao.setEmpresaCompradoraId(empresaOptional.get());
                transacao.setCreditoId(creditoOptional.get());
                transacao.setValorTotal(calcularValorTotal(
                        transacaoDTO.creditoId(),
                        transacao.getQuantidadeComprada()));

                return new TransacaoExibicaoDTO(transacaoRepository.save(transacao));

            } else {
                throw new CreditoNotFoundException("Créditos de carbono não foram encontrados.");
            }
        } else {
            throw new EmpresaNotFoundException("Empresa compradora não encontrada.");
        }
    }

    public Double calcularValorTotal(Long creditoId, Double quantidadeComprada) {
        Optional<CreditoCarbono> creditoCarbono = creditoCarbonoRepository.findById(creditoId);
        return quantidadeComprada * creditoCarbono.get().getValorUnitario();
    }

}
