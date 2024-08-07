package br.com.crux.dao.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.crux.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno>{
	
	
	@Query(value = "SELECT a FROM Aluno a                                                       "
			+ " inner join PessoaFisica pf on a.pessoasFisica = pf                              "
			+ " inner join Instituicao inst on inst.id = pf.idInstituicao                       "
			+ "   where 1=1                                                                     "
			+ "     and inst.id = ?1                                                            "
			+ "     and (?2 is null or a.id = ?2)                                               "
			+ "     and (?3 is null or pf.nomeMae = ?3)                                         "
			+ "     and (?4 is null or ((?4 = '0' and pf.cpf is null) or pf.cpf = ?4 ))         "
			+ " order by pf.nome asc                                                            ")
	public Optional<List<Aluno>> findByFilter(Long idInstituicao, Long idAluno, String idPessoaFisicaMae, String cpfPessoaFisicaAluno);	
	
	
	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Aluno>> findByUnidade(Long idUnidade);

	@Query(value = "SELECT a FROM Aluno a                                                       "
			+ " INNER JOIN PessoaFisica pf on a.pessoasFisica = pf                              "
			+ " INNER JOIN Instituicao inst on inst.id = pf.idInstituicao                       "
			+ "   WHERE 1 = 1                                                                     "
			+ "     AND inst.id = ?1                                                            "
			+ " ORDER BY pf.nome ASC                                                            ")
	public Optional<List<Aluno>> findByInstituicao(Long idInstituicao);

	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join PessoaFisica pf on a.pessoasFisica = pf "
			+ " INNER JOIN Instituicao inst ON inst.id = pf.idInstituicao "
			+ " where Upper(pf.nome) like %?1% "
			+ " AND inst.id = ?2 "
			+ " ORDER BY pf.nome asc")
	public Optional<List<Aluno>> findAlunosByNome(String nome, Long idInstituicao);
	
	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Aluno SET observacaoDeclaracaoMatricula = ?1, dataDeclaracaoMatricula = ?2 WHERE id in ?3" )
	public int updateObservacaoDeclaracaoMatricula(String texto, LocalDateTime data, List<Long> listaIdsAlunos);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Aluno SET observacaoDeclaracaoPasse = ?1, dataDeclaracaoPasse = ?2 WHERE id in ?3" )
	public int updateObservacaoDeclaracaoPasse(String texto, LocalDateTime data, List<Long> listaIdsAlunos);

	
}
