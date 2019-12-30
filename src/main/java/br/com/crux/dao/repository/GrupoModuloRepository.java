package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GruposModulo;
import br.com.crux.entity.Unidade;

@Repository
public interface GrupoModuloRepository extends JpaRepository<GruposModulo, Long>{
	
	
	public Optional<List<GruposModulo>> findByUnidade(Unidade unidade);


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
	
	@Query("SELECT gm FROM GruposModulo gm "
			+ " inner join Modulo modulo on modulo = gm.modulo "
			+ " inner join PerfilAcesso pa on pa = gm.perfilAcesso "
			+ " inner join Unidade u on u = gm.unidade"
			+ " where u.idUnidade       = ?3 "
			+ "   and modulo.id         = ?1 "
			+ "   and pa.id             = ?2")
	public Optional<GruposModulo> findByIdModuloAndIdPerfilAcessoAndIdUnidade(Long idModulo, Long idPerfilAcesso, Long idUnidade);
}
