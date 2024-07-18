package br.com.crux.dao.repository;

import br.com.crux.entity.EixoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EixoAtividadeRepository extends JpaRepository<EixoAtividade, Long>{

	@Query(value = "SELECT e "
			+ "	FROM EixoAtividade e                      "
			+ " 	INNER JOIN Instituicao ins on ins.id = e.idInstituicao "
			+ " WHERE ins.id = :idInstituicao                          "
			+ " ORDER BY e.nome                                   ")
	public Optional<List<EixoAtividade>> findByIdInstituicao(Long idInstituicao);

	@Query(value = "SELECT e "
			+ " FROM EixoAtividade e "
			+ " 	INNER JOIN Instituicao ins on ins.id = e.idInstituicao "
			+ " WHERE 1 = 1 "
			+ " 	AND ins.id = ?1 "
			+ "   	AND (?2 IS NULL OR LOWER(e.nome) LIKE ?2) "
			+ "   	AND (?3 IS NULL OR LOWER(e.descricao) LIKE ?3) "
			+ " ORDER BY e.nome DESC ")
	public Optional<List<EixoAtividade>> findByFilters(Long idInstiuicao, String nome, String descricao);

}

