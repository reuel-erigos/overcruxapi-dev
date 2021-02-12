package br.com.crux.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.rule.CamposObrigatoriosAlunoRule;
import br.com.crux.rule.ValidarDuplicidadeCPFRule;
import br.com.crux.to.AlunoTO;

@Component
public class CadastrarAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlunoRepository repository;
	@Autowired private CamposObrigatoriosAlunoRule camposObrigatoriosRule;
	@Autowired private AlunoTOBuilder alunoTOBuilder;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	@Autowired private CadastrarVulnerabilidadesAlunoCmd cadastrarVulnerabilidadesAlunoCmd;
	@Autowired private CadastrarEncaminhaAlunosCmd cadastrarEncaminhaAlunosCmd;
	@Autowired private CadastrarBeneficioSocialPessoaFisicaCmd cadastrarBeneficiosSociaisPFCmd;
	@Autowired private ValidarDuplicidadeCPFRule validarDuplicidadeCPFRule ;
	
	
	public AlunoTO cadastrar(AlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		validarDuplicidadeCPFRule.verificar(to.getPessoaFisica().getCpf(), to.getPessoaFisica().getId());
		
		to.setDataCadastro(LocalDateTime.now());
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		Aluno entity = alunoTOBuilder.build(to);

		entity.setPessoasFisica(cadastrarPessoaFisicaCmd.cadastrar(to.getPessoaFisica()));
		AlunoTO alunoTOSalvo = alunoTOBuilder.buildTO(repository.save(entity));
		
		entity.setMatriculaAluno(String.valueOf(alunoTOSalvo.getId()));
		
		cadastrarVulnerabilidadesAlunoCmd.cadastrar(to.getVulnerabilidades(), alunoTOSalvo);
		
		cadastrarEncaminhaAlunosCmd.cadastrarLista(alunoTOSalvo, to.getEncaminhamentos());

		cadastrarBeneficiosSociaisPFCmd.cadastrarLista(entity.getPessoasFisica(), to.getBenefeciosSociaisPessoaFisica());
		
		return alunoTOSalvo;
	}
}
