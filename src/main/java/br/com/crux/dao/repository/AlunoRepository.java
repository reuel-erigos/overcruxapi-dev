package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	
	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Aluno>> findByUnidade(Long idUnidade);


	@Query(value = "SELECT a FROM Aluno a "
			+ " inner join PessoaFisica pf on a.pessoasFisica = pf"
			+ " where Upper(pf.nome) like %?1%")
	public Optional<List<Aluno>> findAlunosByNome(String nome);
	
}
