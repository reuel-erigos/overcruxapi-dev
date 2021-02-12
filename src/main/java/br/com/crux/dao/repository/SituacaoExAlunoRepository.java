package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.SituacaoExAluno;

@Repository
public interface SituacaoExAlunoRepository extends JpaRepository<SituacaoExAluno, Long> {
	
	@Query(value = "select ex from SituacaoExAluno ex "
			+ " inner join Aluno aluno on aluno = ex.aluno "
			+ " inner join Unidade unidade on unidade = aluno.unidade "
			+ " inner join Instituicao inst on inst = unidade.instituicao "
			+ "where inst.id = ?1 "
			+ " order by aluno.pessoasFisica.nome ")
	public Optional<List<SituacaoExAluno>> getTodosAlunos(Long idInstituicao); 

}
