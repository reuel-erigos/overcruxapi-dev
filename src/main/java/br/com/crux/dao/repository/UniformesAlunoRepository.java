package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.UniformesAluno;

@Repository
public interface UniformesAlunoRepository extends JpaRepository<UniformesAluno, Long>{
	
	
	@Query(value = "SELECT u FROM UniformesAluno u "
			+ " inner join AtividadesAluno ati on ati = u.atividadesAluno"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<UniformesAluno>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT u FROM UniformesAluno u "
			+ " inner join AtividadesAluno ati on ati = u.atividadesAluno"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where aluno.id = ?1")
	public Optional<List<UniformesAluno>> findByAluno(Long idAluno);

	@Query(value = "SELECT u FROM UniformesAluno u "
			+ " inner join AtividadesAluno ati on ati = u.atividadesAluno"
			+ " inner join Atividades atividade on atividade = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where atividade.id = ?1")
	public Optional<List<UniformesAluno>> findByAtividade(Long idAtividade);

	
	@Query(value = "SELECT u FROM UniformesAluno u "
			+ " inner join AtividadesAluno ati on ati = u.atividadesAluno"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where ati.atividade.id = ?2"
			+ "   and aluno.id         = ?1")
	public Optional<List<UniformesAluno>> findByAlunoAndAtividade(Long idAluno, Long idAtividade);
	
	
}
