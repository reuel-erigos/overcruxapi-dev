package br.com.crux.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{
	
	@Query(value = "select p from PessoaFisica p "
			+ " where 1=1                        "
			+ "  and p.cpf           = ?1        "
			+ "  and p.idInstituicao = ?2        ")
	public Optional<PessoaFisica> findByCpfAndInstituicao(String cpf, Long idInstituicao);

	
}
