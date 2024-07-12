package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import br.com.crux.entity.Acoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GrupoAcoes;

@Repository
public interface GrupoAcoesRepository extends JpaRepository<GrupoAcoes, Long>{
	
	@Query(value = "SELECT ga FROM GrupoAcoes ga                          "
			+ " inner join Oficinas atividade on atividade = ga.atividade "
			+ " where 1 = 1                                               "
			+ "   and ga.numeroGrupo = ?1                                 "
			+ "   and atividade.id   = ?2                                 ")
	public Optional<GrupoAcoes> findByNumeroAndAtividade(String numero, Long idAtividade);

	@Query(value = "SELECT ga "
			+ " FROM GrupoAcoes ga "
			+ " 	 INNER JOIN Acoes acao on acao.idGrupoAcao = ga.id "
			+ " WHERE 1 = 1 "
			+ "       AND acao.id = ?1 ")
	public Optional<GrupoAcoes> findByAcaoId(Long idAcao);

	@Query(value = "SELECT ga "
			+ " FROM GrupoAcoes ga "
			+ " 	INNER JOIN Oficinas oficina ON oficina = ga.atividade "
			+ " 	INNER JOIN Unidade uni ON oficina.unidade = uni "
			+ " 	INNER JOIN Instituicao instituicao on instituicao = uni.instituicao "
			+ " 	LEFT OUTER JOIN Turmas turma ON turma.id = oficina.idTurma "
			+ " 	LEFT OUTER JOIN Acoes acao ON acao.idGrupoAcao = ga.id  "
			+ " WHERE 1 = 1 "
			+ " 	AND instituicao.id = ?1 "
			+ "   	AND (?2 IS NULL OR uni.idUnidade = ?2) "
			+ "   	AND (?3 IS NULL OR turma.id = ?3) "
			+ "   	AND (?4 IS NULL OR oficina.id = ?4) "
			+ "   	AND (?5 IS NULL OR acao.id =  ?5) "
			+ "   	AND (?6 IS NULL OR "
			+ " 	   	(?6 = 'A' AND ga.statusAnalise = 'A') OR "
			+ " 	   	(?6 = 'R' AND ga.statusAnalise = 'R') OR "
			+ " 	   	(?6 = 'E' AND ga.statusEnvioAnalise = true  AND ga.statusAnalise IS NULL) OR "
			+ " 	   	(?6 = 'N' AND ga.statusEnvioAnalise = false AND ga.statusAnalise IS NULL)) "
			+ " GROUP BY ga.id "
			+ " ORDER BY ga.numeroGrupo DESC ")
	public Optional<List<GrupoAcoes>> findByFilters(Long idInstiuicao,
			Long idUnidade,
			Long idTurma,
			Long idOficina,
			Long idAcao,
			String statusAnalise);
}
