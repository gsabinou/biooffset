package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.CreditoCadastroDTO;
import br.com.fiap.biooffset.dto.CreditoExibicaoDTO;
import br.com.fiap.biooffset.service.CreditoCarbonoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditoController {

    @Autowired
    private CreditoCarbonoService creditoCarbonoService;

    @PostMapping("/creditos-carbono")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditoExibicaoDTO criarCredito(
            @Valid @RequestBody CreditoCadastroDTO creditoDTO) {
        return creditoCarbonoService.criarCredito(creditoDTO);
    }

    @GetMapping("/creditos-carbono")
    @ResponseStatus(HttpStatus.OK)
    public Page<CreditoExibicaoDTO> listarTodosDisponiveis(
            @PageableDefault(size = 3, page = 0) Pageable paginacao) {
        return creditoCarbonoService.listarTodos(paginacao);
    }
}
