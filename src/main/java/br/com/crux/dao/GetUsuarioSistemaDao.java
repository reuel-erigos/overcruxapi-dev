package br.com.crux.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.UsuarioUnidadeDTO;
import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.entity.UsuariosSistema;

@Component
public class GetUsuarioSistemaDao extends BaseDao {

	@Autowired private UsuarioSistemaRepository usuarioSistemaRepository;
	
	public Optional<UsuariosSistema>  getUsuarioByUsername(String username) {
		Optional<UsuariosSistema> usuarioSistema = usuarioSistemaRepository.findByUsername(username);
		return usuarioSistema;
	}
	
	public List<UsuarioUnidadeDTO> getUsuariosPorUnidade(Long idUnidade) {
		StringBuilder sql = new StringBuilder();
		
	    sql.append(" 	select pf.id_pessoa_fisica,                      ");
	    sql.append("        us.id_usuario,                               ");
	    sql.append("        pf.nm_pessoa_fisica,                         ");
	    sql.append("        COALESCE(pf.ds_email, '')                    ");
	    sql.append("   from pessoas_fisicas pf,                          ");
	    sql.append("        usuarios_sistema us,                         ");
	    sql.append("        usuarios_unidades uu                         ");
	    sql.append(" where us.id_pessoa_fisica = pf.id_pessoa_fisica     ");
	    sql.append("   and us.st_ativo         = 'S'                     ");
	    sql.append("   and uu.id_usuario       = us.id_usuario           ");
	    sql.append("   and uu.id_unidade       = :idUnidade              ");
	    sql.append("   order by pf.nm_pessoa_fisica                      ");
	    
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUnidade", idUnidade);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<UsuarioUnidadeDTO> retorno = new ArrayList<UsuarioUnidadeDTO>();
		values.stream().forEach( colunas -> retorno.add(new UsuarioUnidadeDTO(colunas)));
		
		return retorno;
	    
	}
	
	
}
