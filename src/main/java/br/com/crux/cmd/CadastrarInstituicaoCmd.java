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

	@Autowired private ValidarCadastroInstituicaoRule cadastroInstituicaoRule ;
	@Autowired private InstituicaoTOBuilder toBuilder;
	@Autowired private InstituicaoRepository instituicaoRepository;
	@Autowired private CadastrarListaFuncoesInstituicaoCmd cadastrarListaFuncoesInstituicaoCmd;

	public InstituicaoTO cadastrar(InstituicaoTO to) {
		cadastroInstituicaoRule.validar(to);
		Instituicao entity = toBuilder.build(to);
		
		Instituicao instituicao = instituicaoRepository.save(entity);
		
		cadastrarListaFuncoesInstituicaoCmd.cadastrarLista(instituicao, to.getFuncoesInstituicao());
		
		return toBuilder.buildTO(entity);
	}
}
