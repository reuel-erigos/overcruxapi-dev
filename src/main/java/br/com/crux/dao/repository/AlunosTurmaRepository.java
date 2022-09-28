package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AlunosTurma;

@Repository
public interface AlunosTurmaRepository extends JpaRepository<AlunosTurma, Long>, JpaSpecificationExecutor<AlunosTurma>{
	
	@Query(value = "SELECT at "
			+ "  FROM AlunosTurma at "
			+ " left join AtividadesAluno ta on ta.aluno = at.aluno"
			+ " left join Oficinas oficina on ta.atividade = oficina"
			+ " inner join Aluno aluno on at.aluno = aluno"
			+ " inner join Turmas t on t = at.turma"
			+ " inner join Unidade uni on t.unidade = uni"
			+ " inner join Instituicao instituicao on instituicao = uni.instituicao "
			+ " where 1 = 1 "
			+ "   and (?2 is null or aluno.id = ?2) "
			+ "   and (?3 is null or oficina.id = ?3) "
			+ "   and (?1 is null or t.id = ?1) "
			+ "   and (?4 is null or instituicao.id = ?4) "
			+ " order by t.descricao, aluno.pessoasFisica.nome, t.dataInicioTurma ")
	public Optional<List<AlunosTurma>> filter(Long idTurma, Long idAluno, Long idAtividade, Long idInstituicao);
	
	public List<AlunosTurma> findByAlunoId(Long idAluno);
	
}
