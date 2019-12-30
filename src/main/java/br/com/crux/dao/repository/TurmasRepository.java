package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Turmas;

@Repository
public interface TurmasRepository extends JpaRepository<Turmas, Long>{
	
	@Query("select turma from Turmas turma "
			+ " inner join Unidade uni on uni.idUnidade = turma.unidade.idUnidade "
			+ " inner join Instituicao instituicao on instituicao.id = uni.instituicao.id"
			+ " where instituicao.id = ?1")
	Optional<List<Turmas>> findAllByIdInstituicao(Long idInstituicao);
	
	
	@Query("select turma from Turmas turma "
			+ " inner join Unidade uni on uni.idUnidade = turma.unidade.idUnidade "
			+ " where uni.idUnidade = ?1")
	Optional<List<Turmas>> findAllByIdUnidade(Long idUnidade);
	
	
	@Query("select turma from Turmas turma "
			+ " inner join Programa prog on prog.id = turma.programa.id "
			+ " where prog.id = ?1")
	Optional<List<Turmas>> findAllByIdPrograma(Long idPrograma);
	
	@Query("select turma from Turmas turma "
			+ " inner join Projeto proj on proj.id = turma.projeto.id "
			+ " where proj.id = ?1")
	Optional<List<Turmas>> findAllByIdProjeto(Long idProjeto);
	
	
	@Query("select turma from Turmas turma "
			+ " inner join Programa prog on prog.id = turma.programa.id "
			+ " inner join Projeto proj on proj.id = turma.projeto.id "
			+ " where prog.id = ?1"
			+ "   and proj.id = ?2")
	Optional<List<Turmas>> findAllByIdProjetoAndIdPrograma(Long idPrograma, Long idProjeto);	

	@Query("select turma from Turmas turma "
			+ " inner join Unidade uni on uni.idUnidade = turma.unidade.idUnidade "
			+ " inner join Programa prog on prog.id = turma.programa.id "
			+ " inner join Projeto proj on proj.id = turma.projeto.id "
			+ " where prog.id = ?1"
			+ "   and proj.id = ?2"
			+ "   and uni.idUnidade = ?3")
	Optional<List<Turmas>> findAllByIdProjetoAndIdProgramaAndIdUnidade(Long idPrograma, Long idProjeto, Long idUnidade);	
	
	
	@Query("select turma from Turmas turma "
			+ " inner join Unidade uni on uni.idUnidade = turma.unidade.idUnidade "
			+ " inner join Programa prog on prog.id = turma.programa.id "
			+ " where prog.id = ?1"
			+ "   and uni.idUnidade = ?3")
	Optional<List<Turmas>> findAllByIdProgramaAndIdUnidade(Long idPrograma, Long idUnidade);	

	@Query("select turma from Turmas turma "
			+ " inner join Unidade uni on uni.idUnidade = turma.unidade.idUnidade "
			+ " inner join Projeto proj on proj.id = turma.projeto.id "
			+ " where proj.id = ?1"
			+ "   and uni.idUnidade = ?2")
	Optional<List<Turmas>> findAllByIdProjetoAndIdUnidade(Long idProjeto, Long idUnidade);	
	
	
}
