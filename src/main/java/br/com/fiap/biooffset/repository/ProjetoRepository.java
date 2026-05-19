package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p WHERE p.nome = :nome")
    Optional<Projeto> buscarPorNome(@Param("nome") String nome);

    List<Projeto> findByEmpresaDesenvolvedora_Id(Long empresaDesenvolvedoraId);
}
