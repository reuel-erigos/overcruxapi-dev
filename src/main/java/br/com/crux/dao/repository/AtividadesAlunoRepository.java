package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AtividadesAluno;

@Repository
public interface AtividadesAlunoRepository extends JpaRepository<AtividadesAluno, Long>{
	
	
	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<AtividadesAluno>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Aluno aluno on ati.aluno = aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where aluno.id = ?1")
	public Optional<List<AtividadesAluno>> findByAluno(Long idAluno);

	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Unidade uni on oficina.unidade = uni"
			+ " where oficina.id = ?1")
	public Optional<List<AtividadesAluno>> findByAtividade(Long idAtividade);
	
	
	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Turmas t on t.id = oficina.idTurma"
			+ " where t.id = ?1 ")
	public Optional<List<AtividadesAluno>> findByTurma(Long idTurma);
	
	
	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Aluno aluno on ati.aluno = aluno"
			+ " inner join Turmas t on t.id = oficina.idTurma"
			+ " where aluno.id = ?2 "
			+ " and t.id = ?1")
	public Optional<List<AtividadesAluno>> findByTurmaAndAluno(Long idTurma, Long idAluno);
	

	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Turmas t on t.id = oficina.idTurma"
			+ " where oficina.id = ?2 "
			+ " and t.id = ?1")
	public Optional<List<AtividadesAluno>> findByTurmaAndAtividade(Long idTurma, Long idAtividade);


	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Aluno aluno on ati.aluno = aluno"
			+ " where aluno.id = ?1 "
			+ " and oficina.id = ?2 ")
	public Optional<List<AtividadesAluno>> findByAlunoAndAtividade(Long idAluno, Long idAtividade);
	

	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Aluno aluno on ati.aluno = aluno"
			+ " inner join Turmas t on t.id = oficina.idTurma"
			+ " where aluno.id = ?2 "
			+ " and oficina.id = ?3 "
			+ " and t.id = ?1")
	public Optional<List<AtividadesAluno>> findByTurmaAndAlunoAndAtividade(Long idTurma, Long idAluno, Long idAtividade);

	
	
	@Query(value = "SELECT ati FROM AtividadesAluno ati "
			+ " inner join Oficinas oficina on ati.atividade = oficina"
			+ " inner join Aluno aluno on ati.aluno = aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " inner join Instituicao instituicao on instituicao = uni.instituicao "
			+ " where aluno.id = ?1 "
			+ "   and instituicao.id = ?2 ")
	public Optional<List<AtividadesAluno>> findByAlunoAndInstituicao(Long idAluno, Long idInstituicao);

}
