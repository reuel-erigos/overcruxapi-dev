package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesInstituicaoTOBuilder;
import br.com.crux.dao.repository.FuncoesInstituicaoRepository;
import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;
import br.com.crux.rule.CamposObrigatoriosFuncoesInstituicaoRule;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class CadastrarFuncoesInstituicaoCmd {

	@Autowired private FuncoesInstituicaoRepository colaboradoresProjetoRepository;
	@Autowired private CamposObrigatoriosFuncoesInstituicaoRule camposObrigatoriosFuncoesInstituicaoRule;
	@Autowired private FuncoesInstituicaoTOBuilder funcoesInstituicaoTOBuilder;

	public void cadastrar(FuncoesInstituicaoTO to, Instituicao p) {
		camposObrigatoriosFuncoesInstituicaoRule.verificar(to);
		FuncoesInstituicao entity = funcoesInstituicaoTOBuilder.build(p, to);
		colaboradoresProjetoRepository.save(entity);
	}
}
