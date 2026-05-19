package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
