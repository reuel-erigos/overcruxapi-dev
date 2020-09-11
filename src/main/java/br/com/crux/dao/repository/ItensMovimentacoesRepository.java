
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ItensMovimentacoes;

@Repository
public interface ItensMovimentacoesRepository extends JpaRepository<ItensMovimentacoes, Long> {

	@Query(value = "SELECT i FROM ItensMovimentacoes i"
			+ " inner join Unidade u on i.unidade = u "
			+ " where u.idUnidade = ?1")
	public Optional<List<ItensMovimentacoes>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT im FROM ItensMovimentacoes im "
			+ " inner join Movimentacoes m on m.id = im.idMovimentacao "
			+ " where m.id = ?1")
	public Optional<List<ItensMovimentacoes>> findByIdMovimentacao(Long idMovimentacao);
}
