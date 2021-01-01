package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.BeneficioSocialPessoaFisica;

@Repository
public interface BeneficioSocialPessoaFisicaRepository extends JpaRepository<BeneficioSocialPessoaFisica, Long>{
	
	@Query(value = "SELECT bs FROM BeneficioSocialPessoaFisica bs "
			+ " inner join PessoaFisica pf on bs.pessoaFisica = pf"
			+ " where pf.id = ?1")
	public Optional<List<BeneficioSocialPessoaFisica>> findByPessoaFisica(Long idPessoaFisica);
	
	
}
