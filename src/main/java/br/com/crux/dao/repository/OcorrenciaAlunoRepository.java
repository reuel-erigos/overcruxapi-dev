package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.OcorrenciaAluno;

@Repository
public interface OcorrenciaAlunoRepository extends JpaRepository<OcorrenciaAluno, Long>{
	
	@Query("select oa from OcorrenciaAluno oa "
			+ " inner join Aluno a on a.id = oa.aluno.id"
			+ " inner join Unidade u on u.idUnidade = a.unidade.idUnidade"
			+ " where u.idUnidade = ?1")
	Optional<List<OcorrenciaAluno>> findAllByUnidade(Long idUnidade);

	
	@Query("select oa from OcorrenciaAluno oa "
			+ " inner join TipoOcorrenciaAluno tipo on tipo.id = oa.tipoOcorrenciaAluno.id"
			+ " where tipo.id = ?1")
	Optional<List<OcorrenciaAluno>> findAllByTipoOcorrencia(Long idTipoOcorrencia);
	

	@Query("select oa from OcorrenciaAluno oa "
			+ " inner join Aluno a on a.id = oa.aluno.id"
			+ " where a.id = ?1")
	Optional<List<OcorrenciaAluno>> findAllByAluno(Long idAluno);
	
	
	@Query("select oa from OcorrenciaAluno oa "
			+ " inner join TipoOcorrenciaAluno tipo on tipo.id = oa.tipoOcorrenciaAluno.id"
			+ " inner join Aluno a on a.id = oa.aluno.id"
			+ " inner join Unidade u on u.idUnidade = a.unidade.idUnidade"
			+ " where a.id = ?1"
			+ "   and tipo.id = ?2")
	Optional<List<OcorrenciaAluno>> findAllByAlunoAndTipoOcorrencia(Long idAluno, Long idTipoOcorrencia);
	
	
}
