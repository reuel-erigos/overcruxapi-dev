package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PerspectivaTOBuilder;
import br.com.crux.dao.repository.ObjetivoRepository;
import br.com.crux.entity.Objetivo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosObjetivoRule;
import br.com.crux.to.ObjetivoTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarObjetivoCmd {

	@Autowired private ObjetivoRepository objetivoRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private PerspectivaTOBuilder perspectivaTOBuilder;
	@Autowired private CamposObrigatoriosObjetivoRule camposObrigatoriosObjetivoRule;
	
	
	public void alterar(ObjetivoTO to) {
		Optional<Objetivo> entityOptional = objetivoRepository.findById(to.getIdObjetivo());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Objetivo informado não existe.");
		}
		
		if(Objects.isNull(to.getPerspectiva())) {
			throw new NotFoundException("Perspectiva não informada.");
		}
		
		camposObrigatoriosObjetivoRule.verificar(to.getNome(), to.getDataImplantacao(), to.getPerspectiva().getIdPerspectiva());
		
		Objetivo objetivo = entityOptional.get();

		objetivo.setNome(to.getNome());
		objetivo.setDataImplantacao(to.getDataImplantacao());
		objetivo.setDataTermino(to.getDataTermino());

		objetivo.setPerspectiva(perspectivaTOBuilder.build(to.getPerspectiva()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		objetivo.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		objetivoRepository.save(objetivo);
		
	}
}
