package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AvaliacoesAlunos;

@Repository
public interface AvaliacoesAlunosRepository extends JpaRepository<AvaliacoesAlunos, Long>{
	
	
	@Query(value = "SELECT aa FROM AvaliacoesAlunos aa "
			+ " inner join AtividadesAluno ati on ati = aa.atividadesAluno"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<AvaliacoesAlunos>> findByUnidade(Long idUnidade);

	
	
	@Query(value = "SELECT aa FROM AvaliacoesAlunos aa "
			+ " inner join AtividadesAluno ati on ati = aa.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where a.id = ?1")
	public Optional<List<AvaliacoesAlunos>> findByAtividade(Long idAtividade);	
	

	@Query(value = "SELECT aa FROM AvaliacoesAlunos aa "
			+ " inner join Avaliacoes ava on ava = aa.avaliacoes"
			+ " inner join AtividadesAluno ati on ati = aa.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where a.id = ?1    "
			+ "   and ava.id = ?2  ")
	public Optional<List<AvaliacoesAlunos>> findByAtividadeAndAvaliacao(Long idAtividade, Long idAvaliacao);	

	
	
	@Query(value = "SELECT aa FROM AvaliacoesAlunos aa "
			+ " inner join AtividadesAluno ati on ati = aa.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where aluno.id = ?1")
	public Optional<List<AvaliacoesAlunos>> findByAluno(Long idAluno);		
	
	@Query(value = "SELECT aa FROM AvaliacoesAlunos aa "
			+ " inner join AtividadesAluno ati on ati = aa.atividadesAluno"
			+ " inner join Atividades a on a = ati.atividade"
			+ " inner join Aluno aluno on aluno = ati.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where aluno.id = ?1"
			+ "   and a.id     = ?2")
	public Optional<List<AvaliacoesAlunos>> findByAlunoAndAtividade(Long idAluno, Long idAtividade);		
	
}
