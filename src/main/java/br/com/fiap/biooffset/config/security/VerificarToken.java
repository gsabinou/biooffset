package br.com.fiap.biooffset.config.security;

import br.com.fiap.biooffset.model.Empresa;
import br.com.fiap.biooffset.repository.EmpresaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        // O retorno da linha acima é algo como "Bearer iual23.ksafnd2301.saknfdoi19
        String token = "";

        if (authorizationHeader == null) {
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer", "").trim();
            // A linha acima remove o "Bearer" do authorizationHeader, deixando o token com a variável
            String login = tokenService.validarToken(token);

            UserDetails empresa = empresaRepository.findByCnpj(login);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(empresa, null, empresa.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // A linha acima está aí para "segurar" a autenticação.
        }

        filterChain.doFilter(request, response);

    }
}
