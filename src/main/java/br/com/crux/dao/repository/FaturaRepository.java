
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {


	@Query(value = "SELECT f FROM Fatura f "
		     + " where f.idMovimentacao = ?1")
	public Optional<List<Fatura>> findByIdMovimentacao(Long id);
	

}
