package br.com.crux.dao.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ColaboradoresAtividade;

@Repository
public interface ColaboradoresAtividadeRepository extends JpaRepository<ColaboradoresAtividade, Long> {

	@Query(value = "SELECT col FROM ColaboradoresAtividade col"
			+ " inner join Atividades ati on col.idAtividade = ati.id"
			+ " inner join Unidade u on ati.unidade = u"
			+ " where u.idUnidade = ?1")
	public Optional<List<ColaboradoresAtividade>> findByIdUnidade(Long idUnidade);

	@Query(value = "SELECT col FROM ColaboradoresAtividade col"
			+ " inner join Atividades ati on col.idAtividade = ati.id"
			+ " where ati.id = ?1")
	public Optional<List<ColaboradoresAtividade>> getPorAtividade(Long id);

}
