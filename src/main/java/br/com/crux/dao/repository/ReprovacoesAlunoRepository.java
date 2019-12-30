package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ReprovacoesAluno;

@Repository
public interface ReprovacoesAlunoRepository extends JpaRepository<ReprovacoesAluno, Long>{
	
	
	@Query(value = "SELECT r FROM ReprovacoesAluno r "
			+ " inner join Aluno a on a = r.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<ReprovacoesAluno>> findByUnidade(Long idUnidade);

	
}
