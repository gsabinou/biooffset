package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.dto.ProjetoExibicaoDTO;
import br.com.fiap.biooffset.dto.TransacaoExibicaoDTO;
import br.com.fiap.biooffset.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Esse endpoint apenas foi usado durante o desenvolvimento, neste ponto
    // é muito mais viável usar o /auth/register, localizado no AuthController.
    @PostMapping("/empresas")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaExibicaoDTO criar(@Valid @RequestBody EmpresaCadastroDTO empresaDTO) {
        return empresaService.criarEmpresa(empresaDTO);
    }

    @GetMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public Page<EmpresaExibicaoDTO> listarTodas(
            @PageableDefault(size = 4, page = 0) Pageable paginacao) {
        return empresaService.listarTodas(paginacao);
    }

    @GetMapping("/empresas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmpresaExibicaoDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PutMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmpresaExibicaoDTO> atualizar(
            @Valid @RequestBody EmpresaCadastroDTO empresaDTO) {
        return ResponseEntity.ok(empresaService.atualizar(empresaDTO));
    }

    @DeleteMapping("/empresas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        empresaService.deletar(id);
    }

    @GetMapping(value = "/empresas/projetos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ProjetoExibicaoDTO>> listarProjetos(
            @PathVariable Long id, @PageableDefault(size = 2, page = 0) Pageable paginacao) {
        return ResponseEntity.ok(empresaService.listarProjetos(id, paginacao));
    }

    @GetMapping(value = "/empresas/transacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<TransacaoExibicaoDTO>> listarTransacoes(
            @PathVariable Long id, @PageableDefault(size = 2, page = 0) Pageable paginacao) {
        return ResponseEntity.ok(empresaService.listarTransacoes(id, paginacao));
    }

}
