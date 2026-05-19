package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresas")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaExibicaoDTO criar(@Valid @RequestBody EmpresaCadastroDTO empresaDTO) {
        return empresaService.criarEmpresa(empresaDTO);
    }

    @GetMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public List<EmpresaExibicaoDTO> listarTodas() {
        return empresaService.listarTodas();
    }

}
