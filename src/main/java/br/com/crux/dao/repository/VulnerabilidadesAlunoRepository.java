package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.VulnerabilidadesAluno;

@Repository
public interface VulnerabilidadesAlunoRepository extends JpaRepository<VulnerabilidadesAluno, Long>{
	
	
	@Query(value = "SELECT v FROM VulnerabilidadesAluno v "
			+ " inner join Aluno a on a = v.idAluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<VulnerabilidadesAluno>> findByUnidade(Long idUnidade);

	
	@Query(value = "SELECT ra FROM VulnerabilidadesAluno ra          "
			  + " inner join Aluno a on a = ra.idAluno                     "
			  + " inner join Unidade uni on a.unidade = uni             "
			  + " where uni.idUnidade = ?1                              "
			  + "   and a.id          = ?2                              ")
	public Optional<List<VulnerabilidadesAluno>> findByUnidadeAndAluno(Long idUnidade, Long idAluno);	
}
