
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ReembolsosPagamentos;

@Repository
public interface ReembolsosPagamentosRepository extends JpaRepository<ReembolsosPagamentos, Long> {

	@Query(value = "SELECT rp FROM ReembolsosPagamentos rp                    "
			+ " inner join PagamentosFatura p  on p.id = rp.idPagamentoFatura "
			+ " where p.id = ?1                                               ")
	public Optional<List<ReembolsosPagamentos>> findByIdPagamentoFatura(Long id);

	
	@Query(value = "SELECT rp FROM ReembolsosPagamentos rp                      "
			+ " inner join PagamentosFatura pf  on pf.id = rp.idPagamentoFatura "
			+ " inner join Fatura f on f.id = pf.idFatura                       "
			+ " where f.idMovimentacao = ?1                                     ")
	public Optional<List<ReembolsosPagamentos>> findByIdMovimento(Long idMovimento);

}
