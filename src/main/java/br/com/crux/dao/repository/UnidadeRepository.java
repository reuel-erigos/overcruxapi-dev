package br.com.crux.dao.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long>{

	
	@Query(value = "SELECT u "
		     + "  FROM Unidade u"
		     + " where upper(u.siglaUnidade) = upper(?1)")
	public Optional<Unidade> findBySiglaUnidadeIgnoreCase(String cdUnidade);

	@Query(value = "SELECT u "
			     + "  FROM Unidade u inner join UsuariosUnidade uu on uu.unidade = u "
			     + " where uu.usuarioSistema.idUsuario = ?1")
	public Optional<List<Unidade>> findAllUnidadesDoUsuarioLogado(Long idUsuarioLogado);

	@Query(value = "SELECT u "
		     + "  FROM Unidade u inner join Instituicao i "
		     + "    on u.instituicao = i "
		     + " where i.id = ?1")
	public Optional<List<Unidade>> findAllInstituicao(Long idInstituicao);
	

	@Query(value = "SELECT u "
		     + "  FROM Unidade u inner join UsuariosUnidade uu on uu.unidade = u "
		     + " where uu.usuarioSistema.idUsuario = ?1 "
		     + "   and u.idUnidade = ?2 ")
    public Optional<Unidade> findUnidadeDoUsuarioLogado(Long idUsuarioLogado, Long idUnidade);

	
	@Query("Select u from Unidade u "
			+ "inner join ProjetosUnidade pu on pu.unidade = u "
			+ "inner join Projeto p on pu.projeto = p "
			+ "where p.id = :idProjeto")
	public Optional<List<Unidade>> getUnidadeByProjeto(Long idProjeto);

	@Query("Select u from Unidade u "
			+ "inner join ProgramasUnidade pu on pu.unidade = u "
			+ "inner join Programa p on pu.programa = p "
			+ "where p.id = :idPrograma")
	public Optional<List<Unidade>> getUnidadeByPrograma(Long idPrograma);

}
