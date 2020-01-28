package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{
	
	@Query(value = "SELECT DISTINCT instituicao FROM Instituicao instituicao "
			+ " inner join Unidade unidade on unidade.instituicao = instituicao "
			+ " inner join UsuariosUnidade uu on uu.unidade = unidade "
			+ " inner join UsuariosSistema us on us = uu.usuarioSistema "
			+ " where us.idUsuario = ?1")
	public Optional<List<Instituicao>> getInstituicoesComAcesso(Long idUsuario) ;
	

}
