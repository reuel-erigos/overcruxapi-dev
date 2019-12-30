package br.com.crux.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.com.crux.entity.UsuariosSistema;

@Repository
public interface TrocarSenhaRepository extends JpaRepository<UsuariosSistema, Long>{

	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE UsuariosSistema SET qtdAcessoNegado = 0, stTrocaSenha = false, senha = ?2 WHERE username = ?1" )
	public int updateSenha(String username, String senha);
	
}
