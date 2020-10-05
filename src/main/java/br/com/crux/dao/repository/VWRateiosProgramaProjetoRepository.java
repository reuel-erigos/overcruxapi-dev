package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.view.RateiosProgramaProjeto;

@Repository
public interface VWRateiosProgramaProjetoRepository extends JpaRepository<RateiosProgramaProjeto, Long>{

	@Query("SELECT VW FROM RateiosProgramaProjeto vw "
			+ " WHERE VW.idMovimentacao = ?1")
	public Optional<List<RateiosProgramaProjeto>> getAllPorIdMovimentacao(Long idMovimentacao);
	
	
}
