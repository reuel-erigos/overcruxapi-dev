package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AnexosAcaoPlanejamento;

@Repository
public interface AnexosAcaoPlanejamentoRepository extends JpaRepository<AnexosAcaoPlanejamento, Long>{
	
	@Query("select a from AnexosAcaoPlanejamento anexo "
			+ " inner join Acoes acao on acao.id = anexo.idAcao "
			+ " where acao.id = ?1")
	public Optional<List<AnexosAcaoPlanejamento>> findAllByIdAcao(Long idAcao);
}
