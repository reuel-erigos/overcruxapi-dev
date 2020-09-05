package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RateiosMovimentacoesUnidades;

@Repository
public interface RateiosMovimentacoesUnidadesRepository extends JpaRepository<RateiosMovimentacoesUnidades, Long> {

	@Query(value = "select rm from RateiosMovimentacoesUnidades rm "
			+ " inner join Movimentacoes m on m.id = rm.idMovimentacao "
			+ " where m.id = ?1")
	public Optional<List<RateiosMovimentacoesUnidades>> findByIdMovimento(Long idMovimento);

}
