package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTurmaTOBuilder;
import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.rule.CamposObrigatoriosAlunosTurmaRule;
import br.com.crux.to.AlunosTurmaTO;
import br.com.crux.to.AtividadesAlunoTO;

@Component
public class CadastrarAlunosTurmaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AlunosTurmaRepository repository;
	@Autowired private CamposObrigatoriosAlunosTurmaRule camposObrigatoriosRule;
	@Autowired private AlunosTurmaTOBuilder alunoTurmaTOBuilder;
	@Autowired private CadastrarAtividadesAlunoCmd cadastrarAtividadesAlunoCmd;
	
	
	public AlunosTurmaTO cadastrar(AlunosTurmaTO to) {
		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		AlunosTurma entity = alunoTurmaTOBuilder.build(to);
		entity.setDataCadastro(LocalDateTime.now());

		entity = repository.save(entity);
		
		
		List<AtividadesAlunoTO> atividadesTO = new ArrayList<AtividadesAlunoTO>();
		
		if(Objects.nonNull(to.getTurma().getOficinas())) {
			to.getTurma().getOficinas().stream().forEach(oficinaTO -> {
				AtividadesAlunoTO atividadeTO = new AtividadesAlunoTO();
				
				atividadeTO.setId(null);
				atividadeTO.setAluno(to.getAluno());
				atividadeTO.setAtividade(oficinaTO);
				atividadeTO.setDataAlteracaoAtividade(LocalDateTime.now());
				atividadeTO.setDataCadastroAtividade(LocalDateTime.now());
				atividadeTO.setDataDesvinculacao(to.getDataDesvinculacao());
				atividadeTO.setDataInicioAtividade(to.getDataInicio());
				atividadeTO.setDescDesligamento(to.getDescricaoDesligamento());
				atividadeTO.setUsuarioAlteracao(to.getUsuarioAlteracao());
				
				atividadesTO.add(atividadeTO);
			});
		}
		
		cadastrarAtividadesAlunoCmd.cadastrarAll(atividadesTO);
		
		return alunoTurmaTOBuilder.buildTO(entity);
	}
}
