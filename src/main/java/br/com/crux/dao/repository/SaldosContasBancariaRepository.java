package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.SaldosContasBancaria;

@Repository
public interface SaldosContasBancariaRepository extends JpaRepository<SaldosContasBancaria, Long>{
	
	
	@Query(value = "SELECT saldo FROM SaldosContasBancaria saldo "
			+ " inner join ContasBancaria conta on conta = saldo.contaBancaria "
			+ " inner join Unidade uni on conta.unidade = uni"
			+ " where 1 =1 "
			+ "   and (?1 is null or uni.idUnidade = ?1) "
			+ "   and (?2 is null or conta.tipoContaBancaria = ?2) "
			+ "   and (?3 is null or conta.nomeBanco like ?3) "
			+ "   and (?4 is null or conta.numeroAgencia = ?4) "
			+ "   and (?5 is null or conta.numeroContaBancaria = ?5)")
	public Optional<List<SaldosContasBancaria>> findByFilter(Long idUnidade, 
			                                                 String tipoContaBancaria, 
			                                                 String nomeBanco, 
			                                                 String numeroAgencia, 
			                                                 String numeroContaBancaria);

	@Query(value = "SELECT saldo FROM SaldosContasBancaria saldo "
			+ " inner join ContasBancaria conta on conta = saldo.contaBancaria "
			+ " where conta.id = :id ")
	public Optional<SaldosContasBancaria> getPorConta(Long id);
		
}
