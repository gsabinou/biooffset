package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.TransacaoCadastroDTO;
import br.com.fiap.biooffset.dto.TransacaoExibicaoDTO;
import br.com.fiap.biooffset.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/comprar")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoExibicaoDTO comprarCreditos(
            @Valid @RequestBody TransacaoCadastroDTO transacaoDTO) {
        return transacaoService.comprarCreditos(transacaoDTO);
    }
}
