package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParticipantesAtendimentosTOBuilder;
import br.com.crux.dao.repository.ParticipantesAtendimentosRepository;
import br.com.crux.entity.ParticipantesAtendimentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosParticipantesAtendimentosRule;
import br.com.crux.to.ParticipantesAtendimentosTO;

@Component
public class AlterarParticipantesAtendimentosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ParticipantesAtendimentosRepository repository;
	@Autowired private CamposObrigatoriosParticipantesAtendimentosRule camposObrigatoriosRule;
	@Autowired private ParticipantesAtendimentosTOBuilder participantesAtendimentosTOBuilder;

	public void alterar(ParticipantesAtendimentosTO to) {
		
		ParticipantesAtendimentos entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Participante atendimento informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = participantesAtendimentosTOBuilder.build(to);

		repository.save(entity);

	}
}
