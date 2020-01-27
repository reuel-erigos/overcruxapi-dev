package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GruposModulo;

@Repository
public interface GrupoModuloRepository extends JpaRepository<GruposModulo, Long>{
	
	
	@Query(" SELECT grupo "
			+ " FROM GruposModulo grupo "
			+ " inner join Modulo modulo on grupo.modulo.id = modulo.id"
			+ " inner join Unidade unidade on unidade.idUnidade = grupo.unidade.idUnidade"
			+ "  where modulo.id             = ?1 ")
	public Optional<List<GruposModulo>> findByModulo(Long idModulo);

	@Query(" SELECT grupo "
			+ " FROM GruposModulo grupo "
			+ " inner join Modulo modulo on grupo.modulo.id = modulo.id"
			+ " inner join Unidade unidade on unidade.idUnidade = grupo.unidade.idUnidade"
			+ "  where unidade.idUnidade     = ?1 "
			+ "    and modulo.id             = ?2 "
			+ "  order by modulo.descricao ")
	public Optional<List<GruposModulo>> findByUnidadeAndModulo(Long idUnidade, Long idModulo);
	
	
	@Query(" SELECT grupo "
			+ " FROM GruposModulo grupo "
			+ " inner join Modulo modulo on grupo.modulo.id = modulo.id"
			+ " inner join Unidade unidade on unidade.idUnidade = grupo.unidade.idUnidade"
			+ "  where unidade.idUnidade     = ?1 "
	        + "  order by modulo.descricao ")
	public Optional<List<GruposModulo>> findByIdUnidade(Long idUnidade);
	
	
	@Query(" SELECT grupo "
			+ " FROM GruposModulo grupo "
			+ " inner join Modulo modulo on grupo.modulo.id = modulo.id"
			+ " inner join Instituicao instituicao on instituicao.id = grupo.instituicao.id"
			+ "  where instituicao.id = ?1 "
	        + "  order by modulo.descricao ")
	public Optional<List<GruposModulo>> findByIdInstituicao(Long id);	
	
	
	@Query("SELECT gm FROM GruposModulo gm "
			+ " inner join Modulo modulo on modulo = gm.modulo "
			+ " inner join PerfilAcesso pa on pa = gm.perfilAcesso "
			+ " inner join Instituicao instituicao on instituicao = gm.instituicao"
			+ " where instituicao.id    = ?3 "
			+ "   and modulo.id         = ?1 "
			+ "   and pa.id             = ?2")
	public Optional<GruposModulo> findByIdModuloAndIdPerfilAcessoAndIdInstituicao(Long idModulo, Long idPerfilAcesso, Long idInstituicao);
}
