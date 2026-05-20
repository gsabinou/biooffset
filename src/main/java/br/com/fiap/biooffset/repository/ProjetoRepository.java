package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p WHERE p.nome = :nome")
    Optional<Projeto> buscarPorNome(@Param("nome") String nome);

    Page<Projeto> findByEmpresaDesenvolvedora_Id(Long empresaDesenvolvedoraId, Pageable pageable);
}
