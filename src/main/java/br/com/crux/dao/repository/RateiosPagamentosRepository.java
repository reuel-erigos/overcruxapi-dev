package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.RateiosPagamentos;

@Repository
public interface RateiosPagamentosRepository extends JpaRepository<RateiosPagamentos, Long> {

	@Query(value = "select distinct rp from RateiosPagamentos rp               "
			+ " inner join PagamentosFatura pf on pf.id = rp.idPagamentoFatura "
			+ " inner join Fatura f on f.id = pf.idFatura                      "
			+ " where f.idMovimentacao = ?1                                    ")
	public Optional<List<RateiosPagamentos>> findByIdMovimento(Long idMovimento);

	
	@Query(value = "select distinct rp from RateiosPagamentos rp              "
			+ " inner join PagamentosFatura p  on p.id = rp.idPagamentoFatura "
			+ " where p.id = :id")
	public Optional<List<RateiosPagamentos>> findByIdPagamentoFatura(Long id);

}
