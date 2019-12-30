package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AlunosTurma;

@Repository
public interface AlunosTurmaRepository extends JpaRepository<AlunosTurma, Long>{
	
	
	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join Turmas turma on turma = at.turma"
			+ " inner join Unidade uni on turma.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<AlunosTurma>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join Aluno aluno on at.aluno = aluno"
			+ " where aluno.id = ?1")
	public Optional<List<AlunosTurma>> findByAluno(Long idAluno);

	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " right join AtividadesAluno ati on ati.aluno = at.aluno"
			+ " inner join Atividades atividade on ati.atividade = atividade"
			+ " where atividade.id = ?1")
	public Optional<List<AlunosTurma>> findByAtividade(Long idAtividade);
	
	
	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join Turmas t on t = at.turma"
			+ " where t.id = ?1 ")
	public Optional<List<AlunosTurma>> findByTurma(Long idTurma);
	
	
	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join Aluno aluno on at.aluno = aluno"
			+ " inner join Turmas t on t = at.turma"
			+ " where aluno.id = ?2 "
			+ " and t.id = ?1")
	public Optional<List<AlunosTurma>> findByTurmaAndAluno(Long idTurma, Long idAluno);
	

	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join AtividadesAluno ati on ati.atividade = atividade"
			+ " inner join Atividades atividade on ati.atividade = atividade"
			+ " inner join Turmas t on t.id = atividade.idTurma "
			+ "       and at.turma = t"
			+ " where atividade.id = ?2 "
			+ "   and t.id = ?1")
	public Optional<List<AlunosTurma>> findByTurmaAndAtividade(Long idTurma, Long idAtividade);


	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join AtividadesAluno ta on ta.atividade = atividade"
			+ " inner join Atividades atividade on ta.atividade = atividade"
			+ " inner join Aluno aluno on ta.aluno = aluno "
			+ "     and at.aluno = aluno "
			+ " where aluno.id = ?1 "
			+ "   and atividade.id = ?2 ")
	public Optional<List<AlunosTurma>> findByAlunoAndAtividade(Long idAluno, Long idAtividade);
	

	@Query(value = "SELECT at FROM AlunosTurma at "
			+ " inner join AtividadesAluno ta on ta.aluno = at.aluno"
			+ " inner join Atividades atividade on ta.atividade = atividade"
			+ " inner join Aluno aluno on at.aluno = aluno"
			+ " inner join Turmas t on t = at.turma"
			+ " where aluno.id = ?2 "
			+ " and atividade.id = ?3 "
			+ " and t.id = ?1")
	public Optional<List<AlunosTurma>> findByTurmaAndAlunoAndAtividade(Long idTurma, Long idAluno, Long idAtividade);


}
