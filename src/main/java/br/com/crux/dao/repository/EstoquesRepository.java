package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Estoques;

@Repository
public interface EstoquesRepository extends JpaRepository<Estoques, Long>{
	
	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " where uni.idUnidade = ?1")
	public Optional<List<Estoques>> findByUnidade(Long idUnidade);
	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Material mat on e.material = mat"
			+ " where uni.idUnidade = ?1 "
			+ "   and mat.id        = ?2 ")
	public Optional<List<Estoques>> findByUnidade(Long idUnidade, Long idMaterial);	


	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Programa pro on e.programa = pro"
			+ " where uni.idUnidade = ?1"
			+ "   and pro.id = ?2")
	public Optional<List<Estoques>> findByUnidadeAndPrograma(Long idUnidade, Long idPrograma);

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Programa pro on e.programa = pro"
			+ " inner join Material mat on e.material = mat"
			+ " where uni.idUnidade = ?1"
			+ "   and pro.id        = ?2"
			+ "   and mat.id        = ?3")
	public Optional<List<Estoques>> findByUnidadeAndProgramaAndMaterial(Long idUnidade, Long idPrograma, Long idMaterial);
	

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Projeto pro on e.projeto = pro"
			+ " where uni.idUnidade = ?1"
			+ "   and pro.id = ?2")
	public Optional<List<Estoques>> findByUnidadeAndProjeto(Long idUnidade, Long idProjeto);

	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Projeto pro on e.projeto = pro"
			+ " inner join Material mat on e.material = mat"
			+ " where uni.idUnidade = ?1"
			+ "   and pro.id        = ?2"
			+ "   and mat.id        = ?3")
	public Optional<List<Estoques>> findByUnidadeAndProjetoAndMaterial(Long idUnidade, Long idProjeto, Long idMaterial);
	

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Projeto proj on e.projeto = proj"
			+ " inner join Programa prog on e.programa = prog"
			+ " where uni.idUnidade = ?1 "
			+ "   and proj.id = ?2       "
			+ "   and prog.id = ?3       ")
	public Optional<List<Estoques>> findByUnidadeAndProjetoAndPrograma(Long idUnidade, Long idProjeto, Long idPrograma);
	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Unidade uni on e.unidade = uni"
			+ " inner join Projeto proj on e.projeto = proj"
			+ " inner join Programa prog on e.programa = prog"
			+ " inner join Material mat on e.material = mat"
			+ " where uni.idUnidade = ?1 "
			+ "   and proj.id       = ?2 "
			+ "   and prog.id       = ?3 "
			+ "   and mat.id        = ?4")
	public Optional<List<Estoques>> findByUnidadeAndProjetoAndProgramaAndMaterial(Long idUnidade, Long idProjeto, Long idPrograma, Long idMaterial);

	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Programa pro on e.programa = pro"
			+ " where pro.id = ?1")
	public Optional<List<Estoques>> findByPrograma(Long idPrograma);
	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Programa pro on e.programa = pro"
			+ " inner join Material mat on e.material = mat"
			+ " where pro.id = ?1 "
			+ "   and mat.id = ?2 ")
	public Optional<List<Estoques>> findByProgramaAndMaterial(Long idPrograma, Long idMaterial);
	

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Projeto pro on e.projeto = pro"
			+ " where pro.id = ?1")
	public Optional<List<Estoques>> findByProjeto(Long idProjeto);

	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Projeto pro on e.projeto = pro"
			+ " inner join Material mat on e.material = mat"
			+ " where pro.id = ?1"
			+ "   and mat.id = ?2")
	public Optional<List<Estoques>> findByProjetoAndMaterial(Long idProjeto, Long material);

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Material mat on e.material = mat"
			+ " where mat.id = ?1")
	public Optional<List<Estoques>> findByMaterial(Long idMaterial);
	

	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Projeto proj on e.projeto = proj"
			+ " inner join Programa prog on e.programa = prog"
			+ " inner join Material mat on e.material = mat"
			+ " where proj.id       = ?1 "
			+ "   and prog.id       = ?2 "
			+ "   and mat.id        = ?3")
	public Optional<List<Estoques>> findByProjetoAndProgramaAndMaterial(Long idProjeto, Long idPrograma, Long idMaterial);
	
	
	
	@Query(value = "SELECT e FROM Estoques e "
			+ " inner join Projeto proj on e.projeto = proj"
			+ " inner join Programa prog on e.programa = prog"
			+ " where proj.id       = ?1 "
			+ "   and prog.id       = ?2 ")
	public Optional<List<Estoques>> findByProjetoAndPrograma(Long idProjeto, Long idPrograma);
	
	
}
