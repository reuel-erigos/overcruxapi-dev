package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RateiosMovimentacoes;

@Repository
public interface RateiosMovimentacoesRepository extends JpaRepository<RateiosMovimentacoes, Long> {

	@Query(value = "select m from RateiosMovimentacoes m "
			+ " where m.idMovimentacao = ?1")
	public Optional<List<RateiosMovimentacoes>> findByIdMovimento(Long idMovimento);

}
