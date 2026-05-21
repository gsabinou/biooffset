package br.com.fiap.biooffset.controller;

import br.com.fiap.biooffset.config.security.TokenService;
import br.com.fiap.biooffset.dto.EmpresaCadastroDTO;
import br.com.fiap.biooffset.dto.EmpresaExibicaoDTO;
import br.com.fiap.biooffset.dto.LoginDTO;
import br.com.fiap.biooffset.dto.TokenDTO;
import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody @Valid LoginDTO empresaDTO) {

        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        empresaDTO.cnpj(),
                        empresaDTO.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Empresa) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrar(@RequestBody @Valid EmpresaCadastroDTO empresaCadastroDTO) {

        EmpresaExibicaoDTO empresaSalva = empresaService.criarEmpresa(empresaCadastroDTO);

        return ResponseEntity.ok(empresaSalva);
    }

}
