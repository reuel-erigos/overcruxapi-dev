package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RateiosMovimentacoes;

@Repository
public interface RateiosMovimentacoesRepository extends JpaRepository<RateiosMovimentacoes, Long> {

	@Query(value = "select rm from RateiosMovimentacoes rm "
			+ " inner join Movimentacoes m on m.id = rm.idMovimentacao "
			+ " where m.id = ?1")
	public Optional<List<RateiosMovimentacoes>> findByIdMovimento(Long idMovimento);

}
