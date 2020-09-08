
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.PagamentosFatura;

@Repository
public interface PagamentosFaturaRepository extends JpaRepository<PagamentosFatura, Long> {

	@Query(value = "SELECT pf FROM PagamentosFatura pf"
			+ " inner join Fatura f  on pf.idFatura = f.id "
			+ " inner join Movimentacoes m  on f.idMovimentacao = m.id "
			+ " where m.id = :id")
	public Optional<List<PagamentosFatura>> findByIdMovimentacao(Long id);

}
