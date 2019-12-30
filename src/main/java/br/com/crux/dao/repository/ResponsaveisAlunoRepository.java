package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ResponsaveisAluno;

@Repository
public interface ResponsaveisAlunoRepository extends JpaRepository<ResponsaveisAluno, Long>{
	
	
	@Query(value = "SELECT ra FROM ResponsaveisAluno ra          "
			      + " inner join Familiares f on f = ra.familiar "
				  + " inner join Aluno a on a = ra.aluno         "
				  + " inner join Unidade uni on a.unidade = uni  "
				  + " where uni.idUnidade = ?1                   "
				  + "   and f.id = ?2                            ")
	public Optional<List<ResponsaveisAluno>> findByUnidadeAndFamiliar(Long idUnidade, Long idFamiliar);

	
	@Query(value = "SELECT ra FROM ResponsaveisAluno ra          "
		      + " inner join Familiares f on f = ra.familiar "
			  + " inner join Aluno a on a = ra.aluno         "
			  + " inner join Unidade uni on a.unidade = uni  "
			  + " where uni.idUnidade = ?1                   "
			  + "   and f.id          = ?2                   "
			  + "   and a.id          = ?3                   ")
	public Optional<List<ResponsaveisAluno>> findByUnidadeAndFamiliarAndAluno(Long idUnidade, Long idFamiliar, Long idAluno);

	
	@Query(value = "SELECT ra FROM ResponsaveisAluno ra        "
		      + " inner join Familiares f on f = ra.familiar   "
			  + " inner join Aluno a on a = ra.aluno           "
			  + " inner join Unidade uni on a.unidade = uni    "
			  + " where uni.idUnidade      = ?2                "
			  + "   and a.id               = ?1                ")
	public Optional<List<ResponsaveisAluno>> findAllResponsaveisPorAlunoAndUnidade(Long idAluno, Long idUnidade);
	
}
