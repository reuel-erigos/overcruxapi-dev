package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Acoes;

@Repository
public interface AcaoRepository extends JpaRepository<Acoes, Long>{
	
	
	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join GrupoAcoes ga on ga.id = acao.idGrupoAcao  "
			+ " inner join Oficinas oficina on oficina = ga.atividade "
			+ " inner join Unidade uni on oficina.unidade = uni"
			+ " where 1 =1 "
			+ "   and (?1 is null or uni.idUnidade = ?1) "
			+ "   and (?2 is null or oficina.id = ?2) "
			+ "   and (?3 is null or acao.id =  ?3) ")
	public Optional<List<Acoes>> findByFilterSemTurma(Long idUnidade, 
					                                  Long idOficina, 
					                                  Long idAcao);
	

	
	@Query(value = "SELECT acao FROM Acoes acao                       "
			+ " inner join GrupoAcoes ga on ga.id = acao.idGrupoAcao  "
			+ " inner join Oficinas oficina on oficina = ga.atividade "
			+ " inner join Turmas turma on turma.id = oficina.idTurma "
			+ " inner join Unidade uni on turma.unidade = uni         "
			+ " where 1 =1                                            "
			+ "   and (?1 is null or uni.idUnidade = ?1)              "
			+ "   and (?2 is null or turma.id = ?2)                   "
			+ "   and (?3 is null or oficina.id = ?3)                 "
			+ "   and (?4 is null or acao.id =  ?4)                   ")
	public Optional<List<Acoes>> findByFilterComTurma(Long idUnidade,
			                                          Long idTurma,
					                                  Long idOficina, 
					                                  Long idAcao);



	@Query(value = "SELECT acao "
			+ " FROM Acoes acao "
			+ " INNER JOIN Instituicao instituicao on instituicao.id = acao.idInstituicao "
			+ " LEFT OUTER JOIN GrupoAcoes ga ON ga.id = acao.idGrupoAcao  "
			+ " LEFT OUTER JOIN Oficinas oficina ON oficina = ga.atividade "
			+ " LEFT OUTER JOIN Turmas turma ON turma.id = oficina.idTurma "
			+ " LEFT OUTER JOIN Unidade uni ON turma.unidade = uni "
			+ " WHERE 1 = 1 "
			+ "   AND instituicao.id = ?1 "
			+ "   AND (?2 IS NULL OR uni.idUnidade = ?2) "
			+ "   AND (?3 IS NULL OR turma.id = ?3) "
			+ "   AND (?4 IS NULL OR oficina.id = ?4) "
			+ "   AND (?5 IS NULL OR acao.id =  ?5) "
			+ "   AND (?6 IS NULL OR "
			+ " 	   (?6 = 'A' AND ga.statusAnalise = 'A') OR "
			+ " 	   (?6 = 'R' AND ga.statusAnalise = 'R') OR "
			+ " 	   (?6 = 'E' AND ga.statusEnvioAnalise = true  AND ga.statusAnalise IS NULL) OR "
			+ " 	   (?6 = 'N' AND ga.statusEnvioAnalise = false AND ga.statusAnalise IS NULL)) ")
	public Optional<List<Acoes>> findByFilters(Long idInstiuicao,
											   Long idUnidade,
											   Long idTurma,
											   Long idOficina,
											   Long idAcao,
											   String statusAnalise);
	
	
	
	
	@Query(value = "SELECT a FROM Acoes a "
			+ " where 1 = 1 "
			+ "   and a.id = ?1 ")
	public Optional<List<Acoes>> findByAcao(Long idAcao);
	
	@Query(value = "SELECT acao FROM Acoes acao "
			+ " inner join Instituicao instituicao on instituicao.id = acao.idInstituicao "
			+ " where instituicao.id = ?1")
	public Optional<List<Acoes>> findByInstituicao(Long idInstituicao);

	@Query(value = "SELECT acao FROM Acoes acao                       "
			+ " inner join GrupoAcoes ga on ga.id = acao.idGrupoAcao  "
			+ " inner join Oficinas oficina on oficina = ga.atividade "
			+ " inner join Unidade uni on oficina.unidade = uni       "
			+ " where uni.idUnidade = ?1                              ")
	public Optional<List<Acoes>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT acao FROM Acoes acao                       "
			+ " inner join GrupoAcoes ga on ga.id = acao.idGrupoAcao  "
			+ " inner join Oficinas oficina on oficina = ga.atividade "
			+ " where oficina.id = ?1                                 ")
	public Optional<List<Acoes>> findByOficina(Long idOficina);	

	
	@Query(value = "SELECT acao FROM Acoes acao                       "
			+ " inner join GrupoAcoes ga on ga.id = acao.idGrupoAcao  "
			+ " inner join Oficinas oficina on oficina = ga.atividade "
			+ " inner join Turmas turma on turma.id = oficina.idTurma "
			+ " where 1 =1                                            "
			+ "   and turma.id = ?1                                   " )
	public Optional<List<Acoes>> findByTurma(Long idTurma);
	

	
	
	@Query(value = "SELECT acao FROM Acoes acao      "
				 + " where 1 =1                      "
				 + "   and acao.idGrupoAcao = ?1     " )
	public Optional<List<Acoes>> findAllByIdGrupo(Long idGrupoAcao);

	
}
