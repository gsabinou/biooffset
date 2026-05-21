package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    UserDetails findByCnpj(String cnpj);

}
