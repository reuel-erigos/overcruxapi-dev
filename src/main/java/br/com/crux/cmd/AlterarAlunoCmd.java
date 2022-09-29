package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.entity.Oficinas;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.constantes.TipoRelatorioBeneficiario;
import br.com.crux.rule.CamposObrigatoriosAlunoRule;
import br.com.crux.rule.ValidarDuplicidadeCPFRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.AtividadesAlunoTO;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.ResponsaveisAlunoTO;
import br.com.crux.to.VulnerabilidadesAlunoTO;
import br.com.crux.to.relatorios.beneficiarios.DadosObservacaoRelatorio;

@Component
public class AlterarAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlunoRepository repository;
	@Autowired private CamposObrigatoriosAlunoRule camposObrigatoriosRule;
	@Autowired private AlunoTOBuilder alunoTOBuilder;
	@Autowired private AtividadesAlunoTOBuilder atividadesAlunoTOBuilder;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private AlterarVulnerabilidadesAlunoCmd alterarVulnerabilidadesAlunoCmd;
	@Autowired private AlterarListaEncaminhamentoAlunosCmd alterarListaEncaminhamentoAlunosCmd;
	@Autowired private AlterarListaBeneficioSocialPessoaFisicaCmd alterarListaBeneficioSocialPessoaFisicaCmd;
	@Autowired private AlterarFamiliaresCmd alterarFamiliaresCmd;
	@Autowired private AlterarResponsaveisAlunoCmd alterarResponsaveisAlunoCmd;
	@Autowired private CadastrarFamiliaresCmd cadastrarFamiliaresCmd;
	@Autowired private CadastrarResponsaveisAlunoCmd cadastrarResponsaveisAlunoCmd;
	@Autowired private CadastrarBeneficioSocialPessoaFisicaCmd cadastrarBeneficiosSociaisPFCmd;
	@Autowired private CadastrarAlunosTurmaCmd cadastrarAlunosTurmaCmd;
	@Autowired private CadastrarAtividadesAlunoCmd cadastrarAtividadesAlunoCmd;
	@Autowired private AlterarUniformesAlunoCmd alterarUniformesAlunoCmd;
	@Autowired private ValidarDuplicidadeCPFRule validarDuplicidadeCPFRule;
	
	public AlunoTO alterar(AlunoTO alunoTO) {
		camposObrigatoriosRule.verificar(alunoTO);
		validarDuplicidadeCPFRule.verificar(alunoTO.getPessoaFisica().getCpf(), alunoTO.getPessoaFisica().getId());
		
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
		
		FamiliaresTO familiarCadastrado = new FamiliaresTO();
		if(!Objects.isNull(alunoTO.getFamiliar())) {
			alunoTO.getFamiliar().setAluno(new AlunoTO());
			alunoTO.getFamiliar().getAluno().setId(alunoTO.getId());
			if(alunoTO.getFamiliar().getId() != null) {
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setId(alunoTO.getFamiliar().getPessoasFisica().getId());
				familiarCadastrado = alterarFamiliaresCmd.alterar(alunoTO.getFamiliar());
				alterarListaBeneficioSocialPessoaFisicaCmd.alterarAll(alunoTO.getFamiliar().getPessoasFisica().getBeneficiosSociaisPessoaFisica(), pessoaFisica);
			} else {
				alunoTO.getFamiliar().setDataCadastro(LocalDateTime.now());
				alunoTO.getFamiliar().setAluno(alunoTO);
				familiarCadastrado = cadastrarFamiliaresCmd.cadastrar(alunoTO.getFamiliar());
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setId(familiarCadastrado.getPessoasFisica().getId());
				cadastrarBeneficiosSociaisPFCmd.cadastrarLista(pessoaFisica, alunoTO.getFamiliar().getPessoasFisica().getBeneficiosSociaisPessoaFisica());
			}
		}
		if(!Objects.isNull(alunoTO.getResponsavelVigente())) {
			if(alunoTO.getResponsavelVigente().getFamiliar() != null && alunoTO.getResponsavelVigente().getFamiliar().getId() != null) {
				alterarResponsaveisAlunoCmd.alterar(alunoTO.getResponsavelVigente(), familiarCadastrado);
			} else {
				List<ResponsaveisAlunoTO> listaResponsaveis = new ArrayList<ResponsaveisAlunoTO>();
				listaResponsaveis.add(alunoTO.getResponsavelVigente());
				cadastrarResponsaveisAlunoCmd.cadastrar(listaResponsaveis, familiarCadastrado);
			}
		}
		if(!Objects.isNull(alunoTO.getAtividades())) {
			for (AtividadesAlunoTO atividadesAlunoTO : alunoTO.getAtividades()) {
				atividadesAlunoTO.getUniformes().stream().forEach(item -> item.setAtividadesAluno(atividadesAlunoTO));
				alterarUniformesAlunoCmd.alterarAll(atividadesAlunoTO.getUniformes(), atividadesAlunoTO.getAtividade().getId());
			}
		}
		
		
		
		Aluno alunoSalvo = repository.save(aluno);
		AlunoTO alunoSalvoTO = alunoTOBuilder.buildTO(alunoSalvo);
		
		if(!CollectionUtils.isEmpty(alunoTO.getMatriculas())) {
			alunoTO.getMatriculas().stream().forEach(matricula -> {
				if(matricula.getId() == null) {
					matricula.setAluno(alunoSalvoTO);
					cadastrarAlunosTurmaCmd.cadastrar(matricula);
				} else {
					matricula.getOficinas().forEach(oficinaTO -> {
						if(oficinaTO.getId() == null) {
							oficinaTO.setAluno(alunoSalvoTO);
							cadastrarAtividadesAlunoCmd.cadastrar(oficinaTO);
						}
					});
				}
			});
			
		}
		
		return alunoSalvoTO;
	}
	
	
	public void salvarTextoObservacaoRelatorio(DadosObservacaoRelatorio  dadosObservacaoRelatorio)  {
		if(dadosObservacaoRelatorio.getTipoRelatorio().equals(TipoRelatorioBeneficiario.PASSE_ESTUDANTIL)) {
			repository.updateObservacaoDeclaracaoPasse(dadosObservacaoRelatorio.getTextoObservacao(), LocalDateTime.now(), dadosObservacaoRelatorio.getListaIdsAlunos());
		}
		if(dadosObservacaoRelatorio.getTipoRelatorio().equals(TipoRelatorioBeneficiario.DECLARACAO)) {
			repository.updateObservacaoDeclaracaoMatricula(dadosObservacaoRelatorio.getTextoObservacao(), LocalDateTime.now(), dadosObservacaoRelatorio.getListaIdsAlunos());
		}
	}
}
