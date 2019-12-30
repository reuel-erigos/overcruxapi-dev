package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AcoesCompetencia;

@Repository
public interface AcoesCompetenciaRepository extends JpaRepository<AcoesCompetencia, Long> {

	@Query(value = "SELECT ac FROM AcoesCompetencia ac " 
					+ " inner join TalentosPf t on ac.talentosPf = t" 
					+ " inner join PessoaFisica pf on t.pessoasFisica = pf" 
					+ " where pf.id = ?1")
	public Optional<List<AcoesCompetencia>> getPorPessoa(Long idPessoa);
}
