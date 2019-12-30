package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.VulnerabilidadesFamiliar;

@Repository
public interface VulnerabilidadesFamiliarRepository extends JpaRepository<VulnerabilidadesFamiliar, Long>{
	
	
	@Query(value = "SELECT v FROM VulnerabilidadesFamiliar v "
			+ " inner join Familiares f on v.familiar = f"
			+ " inner join Aluno a on a = f.aluno"
			+ " inner join Unidade uni on a.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<VulnerabilidadesFamiliar>> findByUnidade(Long idUnidade);


	@Query(value = "SELECT ra FROM VulnerabilidadesFamiliar ra          "
		      + " inner join Familiares f on f = ra.familiar            "
			  + " inner join Aluno a on a = f.aluno                     "
			  + " inner join Unidade uni on a.unidade = uni             "
			  + " where uni.idUnidade = ?1                              "
			  + "   and f.id          = ?2                              ")
	public Optional<List<VulnerabilidadesFamiliar>> findByUnidadeAndFamiliar(Long idUnidade, Long idFamiliar);

}