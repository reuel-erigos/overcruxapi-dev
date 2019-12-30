package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.AlunosTrabalhando;

@Repository
public interface AlunosTrabalhandoRepository extends JpaRepository<AlunosTrabalhando, Long>{
	
	
	@Query(value = "SELECT at FROM AlunosTrabalhando at "
			+ " inner join Aluno aluno on aluno = at.aluno"
			+ " inner join Unidade uni on aluno.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<AlunosTrabalhando>> findByUnidade(Long idUnidade);

	
}
