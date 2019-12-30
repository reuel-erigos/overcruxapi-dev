package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.DocumentosAtividade;

@Repository
public interface DocumentosAtividadeRepository extends JpaRepository<DocumentosAtividade, Long>{
	
	
	@Query(value = "SELECT d FROM DocumentosAtividade d "
			+ " inner join Atividades ati on ati = d.atividade"
			+ " inner join Unidade uni on ati.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<DocumentosAtividade>> findByUnidade(Long idUnidade);

	@Query(value = "SELECT d FROM DocumentosAtividade d "
			+ " inner join Atividades ati on ati = d.atividade"
			+ " where ati.id = ?1")
	public Optional<List<DocumentosAtividade>> getPorAtividade(Long idAtividade);

	
}
