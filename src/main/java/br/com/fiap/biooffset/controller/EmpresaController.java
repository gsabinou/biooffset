package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/empresas/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmpresaExibicaoDTO> buscarPorCnpj(
            @PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmpresaExibicaoDTO> atualizar(
            @Valid @RequestBody EmpresaCadastroDTO empresaDTO) {
        return ResponseEntity.ok(empresaService.atualizar(empresaDTO));
    }

    @DeleteMapping("/empresas/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String cnpj) {
        empresaService.deletar(cnpj);
    }

}
