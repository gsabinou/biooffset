package br.com.fiap.biooffset.repository;

import br.com.fiap.biooffset.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByEmpresaCompradoraId_Id(Long empresaCompradoraIdId, Pageable pageable);

    Page<Transacao> findByCreditoId_Id(Long creditoIdId, Pageable pageable);

}
