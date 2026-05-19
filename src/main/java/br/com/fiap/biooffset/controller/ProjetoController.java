package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.dto.ProjetoCadastroDTO;
import br.com.fiap.biooffset.dto.ProjetoExibicaoDTO;
import br.com.fiap.biooffset.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/projetos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoExibicaoDTO criarProjeto (@Valid @RequestBody ProjetoCadastroDTO projetoDTO) {
        return projetoService.criarProjeto(projetoDTO);
    }

    @GetMapping("/projetos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoExibicaoDTO> listarTodos() {
        return projetoService.listarTodos();
    }

    @GetMapping(value = "/projetos", params = "nome")
    public ResponseEntity<ProjetoExibicaoDTO> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(projetoService.buscarPorNome(nome));
    }

    @PutMapping("/projetos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjetoExibicaoDTO> atualizar(
            @Valid @RequestBody ProjetoCadastroDTO projetoDTO) {
        return ResponseEntity.ok(projetoService.atualizar(projetoDTO));
    }

    @DeleteMapping("/projetos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        projetoService.deletar(id);
    }
}
