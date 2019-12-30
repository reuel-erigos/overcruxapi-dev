package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UsuariosUnidadeTOBuilder;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.rule.CamposObrigatoriosUsuariosUnidadeRule;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class CadastrarUsuariosUnidadeCmd {

	@Autowired private CamposObrigatoriosUsuariosUnidadeRule camposObrigatoriosUsuariosUnidadeRule;
	@Autowired private UsuariosUnidadeTOBuilder usuarioUnidadeTOBuilder;
	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;

	public void cadastrar(UsuariosUnidadesTO usuarioUnidade, UsuariosSistema usuarioSalvo) {
		camposObrigatoriosUsuariosUnidadeRule.verificar(usuarioUnidade);
		
		usuarioUnidade.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		UsuariosUnidade entity = usuarioUnidadeTOBuilder.build(usuarioUnidade);
		entity.setUsuarioSistema(usuarioSalvo);
			
		Unidade unidade = getUnidadeCmd.getById(usuarioUnidade.getUnidade().getIdUnidade());
		entity.setUnidade(unidade);
		
		usuariosUnidadeRepository.save(entity);
	}
	
	
	public void cadastrarAll(List<UsuariosUnidadesTO> lista, UsuariosSistema usuarioSalvo) {
		lista.stream().forEach( usuarioUnidade -> {
			cadastrar(usuarioUnidade, usuarioSalvo);
		});
	}
}
