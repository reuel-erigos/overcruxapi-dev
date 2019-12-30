package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.AcessoDTO;
import br.com.crux.dao.dto.PerfilAcessoUsuarioDTO;
import br.com.crux.dao.repository.TrocarSenhaRepository;

@Component
public class AcessoDao extends BaseDao{
	
	@Autowired 
	private TrocarSenhaRepository trocarSenhaRepository;
	
	public void trocarSenha(String username, String senha) {
		trocarSenhaRepository.updateSenha(username, senha);
	}
	
	public List<PerfilAcessoUsuarioDTO> getPerfilAcessoDoUsuario(Long idUnidade,Long idUsuario,Long idModulo) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("	select us.id_usuario,                                         ");
		sql.append("       us.nm_username,                                            ");
		sql.append("       u.id_unidade,                                              ");
		sql.append("       u.nm_unidade,                                              ");
		sql.append("       m.id_modulo,                                               ");
		sql.append("       m.ds_modulo,                                               ");
		sql.append("       gm.id_grupo_modulo,                                        ");
		sql.append("       gm.nm_grupo,                                               ");
		sql.append("       gm.tx_descricao_grupo,                                     ");
		sql.append("       ug.id_usuario_grupo                                        ");
		sql.append("  from usuarios_grupos ug,                                        ");
		sql.append("       usuarios_sistema us,                                       ");
		sql.append("       modulos m,                                                 ");
		sql.append("       grupos_modulos gm,                                         ");
		sql.append("       unidades u,                                                ");
		sql.append("       usuarios_unidades uu                                       ");
		sql.append(" where ug.id_grupo_modulo  = gm.id_grupo_modulo                   ");
		sql.append("   and gm.id_modulo        = m.id_modulo                          ");
		sql.append("   and ug.id_usuario       = us.id_usuario                        ");
		sql.append("   and us.id_usuario       = uu.id_usuario                        ");
		sql.append("   and uu.id_unidade       = u.id_unidade                         ");
		sql.append("   and gm.id_unidade       = u.id_unidade                         ");
		sql.append("   and u.id_unidade        = :idUnidade                           ");
		sql.append("   and m.nm_modulo         not like 'TB_REFERENCIA%'              ");
		sql.append("   and m.modulo_pai is not null                                   ");
		
		if(Objects.nonNull(idModulo)) {
			sql.append("   and m.id_modulo         = :idModulo                        ");
		}
		if(Objects.nonNull(idUsuario)) {
			sql.append("   and us.id_usuario       = :idUsuario                       ");
		}
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUnidade", idUnidade);
		if(Objects.nonNull(idModulo)) {
			query.setParameter("idModulo",  idModulo);
		}
		if(Objects.nonNull(idUsuario)) {
			query.setParameter("idUsuario", idUsuario);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<PerfilAcessoUsuarioDTO> retorno = new ArrayList<PerfilAcessoUsuarioDTO>();
		values.stream().forEach( colunas -> retorno.add(new PerfilAcessoUsuarioDTO(colunas)));
		
		return retorno;
		
	}
	
	
	
	public List<AcessoDTO> getPerfilAcesso(Long idUnidade,Long idUsuario,Long idModulo) {
		StringBuilder sql = new StringBuilder();

	  	sql.append(" select m.id_modulo,                                              ");
	  	sql.append("        m.nm_modulo,                                              ");
	  	sql.append("        pa.cs_consulta,                                           ");
	  	sql.append("        pa.cs_altera,                                             ");
	  	sql.append("        pa.cs_deleta,                                             ");
	  	sql.append("        pa.cs_insere                                              ");
	    sql.append("   from usuarios_grupos ug,                                       ");   
	    sql.append("        usuarios_sistema us,                                      ");   
	    sql.append("        modulos m,                                                ");   
	    sql.append("        grupos_modulos gm,                                        ");
	    sql.append("        perfis_acessos pa                                         ");   
	    sql.append("  where ug.id_grupo_modulo  = gm.id_grupo_modulo                  ");   
	    sql.append("    and gm.id_modulo        = m.id_modulo                         ");   
	    sql.append("    and ug.id_usuario       = us.id_usuario                       ");   
	    sql.append("    and us.st_ativo         = 'S'                                 ");
	    sql.append("    and pa.id_perfil_acesso = gm.id_perfil_acesso                 ");                
	    sql.append("    and gm.id_unidade       = :idUnidade                          ");                    
	    sql.append("    and us.id_usuario       = :idUsuario                          ");
	    sql.append("    and m.id_modulo         = :idModulo                           ");
		
	    
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUnidade", idUnidade);
		query.setParameter("idModulo",  idModulo);
		query.setParameter("idUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AcessoDTO> retorno = new ArrayList<AcessoDTO>();
		values.stream().forEach( colunas -> retorno.add(new AcessoDTO(colunas)));
		
		return retorno;
		
	}

}
