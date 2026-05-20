package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.CreditoCarbono;
import br.com.fiap.biooffset.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditoCarbonoRepository extends JpaRepository<CreditoCarbono, Long> {

    Page<CreditoCarbono> findByProjetoId_Id(Long projetoIdId, Pageable pageable);
}
