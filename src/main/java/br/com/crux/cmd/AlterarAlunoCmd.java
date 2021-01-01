package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAlunoRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.VulnerabilidadesAlunoTO;

@Component
public class AlterarAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlunoRepository repository;
	@Autowired private CamposObrigatoriosAlunoRule camposObrigatoriosRule;
	@Autowired private AlunoTOBuilder alunoTOBuilder;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private AlterarVulnerabilidadesAlunoCmd alterarVulnerabilidadesAlunoCmd;
	@Autowired private AlterarListaEncaminhamentoAlunosCmd alterarListaEncaminhamentoAlunosCmd;
	@Autowired private AlterarListaBeneficioSocialPessoaFisicaCmd alterarListaBeneficioSocialPessoaFisicaCmd;
	
	public AlunoTO alterar(AlunoTO alunoTO) {
		camposObrigatoriosRule.verificar(alunoTO);
		Aluno aluno = repository.findById(alunoTO.getId()).orElseThrow((() -> new NotFoundException("Aluno informado não existe.")));
		
		alunoTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		aluno = alunoTOBuilder.build(alunoTO);
		aluno.setPessoasFisica(alterarPessoaFisicaCmd.alterar(alunoTO.getPessoaFisica()));
		
		if(Objects.isNull(alunoTO.getVulnerabilidades())) {
			alunoTO.setVulnerabilidades(new ArrayList<VulnerabilidadesAlunoTO>());
		}
		if(Objects.isNull(alunoTO.getEncaminhamentos())) {
			alunoTO.setEncaminhamentos(new ArrayList<EncaminhaAlunosTO>());
		}
		
		alterarVulnerabilidadesAlunoCmd.alterarAll(alunoTO.getVulnerabilidades(), alunoTO);
		alterarListaEncaminhamentoAlunosCmd.alterarAll(alunoTO.getEncaminhamentos(), aluno);
		alterarListaBeneficioSocialPessoaFisicaCmd.alterarAll(alunoTO.getBenefeciosSociaisPessoaFisica(), aluno.getPessoasFisica());
		
		Aluno alunoSalvo = repository.save(aluno);
		
		return alunoTOBuilder.buildTO(alunoSalvo);
	}
}
