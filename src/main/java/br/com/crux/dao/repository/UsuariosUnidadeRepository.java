package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.entity.UsuariosUnidade;

@Repository
public interface UsuariosUnidadeRepository extends JpaRepository<UsuariosUnidade, Long> {

	
	@Query(value = "select uu from UsuariosUnidade uu "
			+ " inner join UsuariosSistema us on us.idUsuario =  uu.usuarioSistema.idUsuario "
			+ " where us.idUsuario = ?1")
	public Optional<List<UsuariosUnidade>> findByUsuarioSistema(Long idUsuario);

	public Optional<List<UsuariosUnidade>> findByUnidade(Unidade unidade);
	
	public void deleteByUsuarioSistema(UsuariosSistema usuariosSistema);

}
