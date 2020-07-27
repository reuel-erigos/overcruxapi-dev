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
		     + " where upper(u.siglaUnidade) = upper(?1)"
		     + "  order by u.ordemExibicao ")
	public Optional<Unidade> findBySiglaUnidadeIgnoreCase(String cdUnidade);

	@Query(value = "SELECT u "
			     + "  FROM Unidade u inner join UsuariosUnidade uu on uu.unidade = u "
			     + " where uu.usuarioSistema.idUsuario = ?1 "
			     + " order by u.ordemExibicao ")
	public Optional<List<Unidade>> findAllUnidadesDoUsuarioLogado(Long idUsuarioLogado);

/*	
	@Query(value = "SELECT u "
				+ "  FROM Unidade  "
				+ "    where id_instituicao in ( "
				+ "              select distinct ii.id_instituicao from instituicoes ii, unidades ui, usuarios_unidades uui "
				+ "               where ii.id_instituicao = ui.id_instituicao "                  
				+ "                   and ui.id_unidade = uui.id_unidade "
				+ "                   and uui.id_Usuario = ?1) "
				+ "   order by u.ordemExibicao ")
	public Optional<List<Unidade>> findAllUnidadesDaInsttuicaoDoUsuarioLogado(Long idUsuarioLogado);
*/
	
	@Query(value = "SELECT distinct u "
			     + "  FROM Unidade u "
	 		     + "      inner join Instituicao i on i = u.instituicao "
	 		     + "      inner join Unidade uns on uns.instituicao = i "
	 		     + "      inner join UsuariosUnidade uu on uu.unidade = uns "
	 		     + "      inner join UsuariosSistema usuario on usuario = uu.usuarioSistema "
	 		     + " where usuario.idUsuario = ?1 "
	 		     + " order by u.ordemExibicao ")
	public Optional<List<Unidade>> findAllUnidadesDaInsttuicaoDoUsuarioLogado(Long idUsuarioLogado);
	
	@Query(value = "SELECT u "
		     + "  FROM Unidade u inner join Instituicao i "
		     + "    on u.instituicao = i "
		     + " where i.id = ?1 "
		     + " order by u.ordemExibicao ")
	public Optional<List<Unidade>> findAllInstituicao(Long idInstituicao);
	

	@Query(value = "SELECT u "
		     + "  FROM Unidade u inner join UsuariosUnidade uu on uu.unidade = u "
		     + " where uu.usuarioSistema.idUsuario = ?1 "
		     + "   and u.idUnidade = ?2 "
		     + " order by u.ordemExibicao ")
    public Optional<Unidade> findUnidadeDoUsuarioLogado(Long idUsuarioLogado, Long idUnidade);

	
	@Query("Select u from Unidade u "
			+ "   inner join ProjetosUnidade pu on pu.unidade = u "
			+ "   inner join Projeto p on pu.projeto = p "
			+ " where p.id = :idProjeto "
			+ "  order by u.ordemExibicao ")
	public Optional<List<Unidade>> getUnidadeByProjeto(Long idProjeto);

	@Query("Select u from Unidade u "
			+ "  inner join ProgramasUnidade pu on pu.unidade = u "
			+ "   inner join Programa p on pu.programa = p "
			+ " where p.id = :idPrograma "
			+ " order by u.ordemExibicao ")
	public Optional<List<Unidade>> getUnidadeByPrograma(Long idPrograma);

}
