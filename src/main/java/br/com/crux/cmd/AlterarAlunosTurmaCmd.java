package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTurmaTOBuilder;
import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAlunosTurmaRule;
import br.com.crux.to.AlunosTurmaTO;

@Component
public class AlterarAlunosTurmaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AlunosTurmaRepository repository;
	@Autowired private CamposObrigatoriosAlunosTurmaRule camposObrigatoriosRule;
	@Autowired private AlunosTurmaTOBuilder toBuilder;
	@Autowired private AlterarAtividadesAlunoCmd alterarAtividadesAlunoCmd;
	

	public void alterar(AlunosTurmaTO to) {
		AlunosTurma entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Oficina do Aluno informada não existe."));

		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity = toBuilder.build(to);
		entity.setDataAlteracao(LocalDateTime.now());

		repository.save(entity);
		
		if(Objects.nonNull(to.getDataDesvinculacao()) && Objects.nonNull(to.getOficinas())) {
			to.getOficinas().forEach(o -> {
				if(Objects.isNull(o.getDataDesvinculacao())) {
					o.setDataDesvinculacao(to.getDataDesvinculacao());
					o.setDescDesligamento(to.getDescricaoDesligamento());
				}
			});
		}
		
		alterarAtividadesAlunoCmd.alterarAll(to.getOficinas(), to.getTurma());

	}
}
