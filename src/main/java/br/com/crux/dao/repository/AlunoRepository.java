package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	
	@Query(value = "SELECT a FROM Aluno a                                                       "
			+ " inner join PessoaFisica pf on a.pessoasFisica = pf                              "
			+ " inner join Instituicao inst on inst.id = pf.idInstituicao                       "
			+ "   where 1=1                                                                     "
			+ "     and inst.id = ?1                                                            "
			+ "     and (?2 is null or a.id = ?2)                                               "
			+ "     and (?3 is null or pf.nomeMae = ?3)                                         "
			+ "     and (?4 is null or pf.cpf = ?4)                                             "
			+ " order by pf.nome asc                                                           ")
	public Optional<List<Aluno>> findByFilter(Long idInstituicao, Long idAluno, String idPessoaFisicaMae, String cpfPessoaFisicaAluno);	
	
	
	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Aluno>> findByUnidade(Long idUnidade);


	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join PessoaFisica pf on a.pessoasFisica = pf"
			+ " where Upper(pf.nome) like %?1%")
	public Optional<List<Aluno>> findAlunosByNome(String nome);
	
}
