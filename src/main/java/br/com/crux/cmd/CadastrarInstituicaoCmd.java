package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.InstituicaoTOBuilder;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.rule.ValidarCadastroInstituicaoRule;
import br.com.crux.to.InstituicaoTO;

@Component
public class CadastrarInstituicaoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ValidarCadastroInstituicaoRule cadastroInstituicaoRule ;
	@Autowired private InstituicaoTOBuilder toBuilder;
	@Autowired private InstituicaoRepository instituicaoRepository;

	public InstituicaoTO cadastrar(InstituicaoTO to) {
		cadastroInstituicaoRule.validar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Instituicao instituicao = toBuilder.build(to);
		
		return toBuilder.buildTO(instituicaoRepository.save(instituicao));
	}
}
