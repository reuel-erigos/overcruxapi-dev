
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

	public Optional<List<Fatura>> findByMovimentacao(Movimentacoes movimentacoes);
	
	
	@Query(value = "SELECT f FROM Fatura f "
		     + "inner join Movimentacoes m on f.movimentacao = m "
		     + " where m.id = ?1")
	public Optional<List<Fatura>> findByIdMovimentacao(Long id);
	

}
