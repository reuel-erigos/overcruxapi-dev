package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.GruposModulo;
import br.com.crux.entity.Modulo;
import br.com.crux.entity.UsuariosGrupo;
import br.com.crux.entity.UsuariosSistema;

@Repository
public interface UsuariosGrupoRepository extends JpaRepository<UsuariosGrupo, Long> {

	
	public Optional<UsuariosGrupo> findByGruposModulo(GruposModulo grupoModulo);
	
	public Optional<UsuariosGrupo> findByGruposModuloAndUsuariosSistema(GruposModulo grupoModulo, UsuariosSistema usuario);
	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ "inner join GruposModulo gm on ug.gruposModulo = gm "
			+ "inner join Modulo m on gm.modulo = m "
			+ "inner join UsuariosSistema us on us.idUsuario = ug.usuariosSistema.idUsuario "
			+ " where m.id = ?1 "
			+ "   and gm.id = ?2"
			+ "   and us.idUsuario = ?3")
	public Optional<UsuariosGrupo> getPorModuloAndGrupoModuloAndUsuario(Long idModulo, Long idGrupoModulo, Long idUsuario);
	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ " inner join UsuariosSistema us on us = ug.usuariosSistema"
			+ " inner join GruposModulo gm on gm = ug.gruposModulo"
			+ " inner join Modulo m on m = gm.modulo"
			+ "   where ug.idUsuarioGrupo  = ?1")
	public Optional<UsuariosGrupo> getById(Long idUsuarioGrupo);

	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ " inner join UsuariosSistema us on us = ug.usuariosSistema"
			+ " inner join GruposModulo gm on gm = ug.gruposModulo"
			+ " inner join Unidade uni on uni = gm.unidade"
			+ " inner join Modulo m on m = gm.modulo"
			+ "   where us.idUsuario   = ?1"
			+ "     and m.id           = ?2"
			+ "     and uni.idUnidade  = ?3")
	public Optional<List<UsuariosGrupo>> getPermissoes(Long idUsuario, Long idModulo, Long idUnidade);
	
	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ " inner join UsuariosSistema us on us = ug.usuariosSistema"
			+ " inner join GruposModulo gm on gm = ug.gruposModulo"
			+ " inner join Unidade uni on uni = gm.unidade"
			+ "   where us.idUsuario   = ?1"
			+ "     and uni.idUnidade  = ?2")
	public Optional<List<UsuariosGrupo>> getPermissoesNaUnidade(Long idUsuario, Long idUnidade);	
	
	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ " inner join UsuariosSistema us on us = ug.usuariosSistema"
			+ " inner join GruposModulo gm on gm = ug.gruposModulo"
			+ " inner join Modulo m on m = gm.modulo "
			+ "   where us.idUsuario  = ?1 "
			+ "     and exists ( select mp from Modulo mp "
			+ "                    where mp.id = ?2 "
			+ "                      and mp = m.moduloPai)")
	public Optional<List<UsuariosGrupo>> getModulosFilhosComMesmoModuloPai(Long idUsuario, Long idModuloPai);

	
	@Query(value = "SELECT ug FROM UsuariosGrupo ug "
			+ " inner join UsuariosSistema us on us = ug.usuariosSistema"
			+ " inner join GruposModulo gm on gm = ug.gruposModulo"
			+ " inner join Modulo m on m = gm.modulo "
			+ "   where us.idUsuario  = ?1 "
			+ "     and m = ?2 ")
	public Optional<List<UsuariosGrupo>> getModulosPai(Long idUsuario, Modulo moduloPai);
	
}
