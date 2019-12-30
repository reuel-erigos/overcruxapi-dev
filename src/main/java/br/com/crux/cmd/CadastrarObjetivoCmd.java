package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PerspectivaTOBuilder;
import br.com.crux.dao.repository.ObjetivoRepository;
import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.entity.Objetivo;
import br.com.crux.entity.Perspectiva;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.rule.CamposObrigatoriosObjetivoRule;
import br.com.crux.to.ObjetivoTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarObjetivoCmd {

	@Autowired private PerspectivaRepository perspectivaRepository;
	@Autowired private ObjetivoRepository objetivoRepository;  
	@Autowired private PerspectivaTOBuilder perspectivaTOBuilder; 
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosObjetivoRule camposObrigatoriosObjetivoRule;
	
	public void cadastrar(ObjetivoTO to) {
		
		if(Objects.isNull(to.getPerspectiva())) {
			throw new ParametroNaoInformadoException("Perspectiva não foi informada.");
		}
		
		Optional<Perspectiva> perspectiva = perspectivaRepository.findById(to.getPerspectiva().getIdPerspectiva());
		if(!perspectiva.isPresent()) {
			throw new NotFoundException("Perspectiva informada não existe.");
		}
		
		camposObrigatoriosObjetivoRule.verificar(to.getNome(), to.getDataImplantacao(), to.getPerspectiva().getIdPerspectiva());
		
		Objetivo entity = new Objetivo();

		entity.setNome(to.getNome());
		entity.setDataImplantacao(to.getDataImplantacao());
		entity.setDataTermino(to.getDataTermino());
		entity.setPerspectiva(perspectivaTOBuilder.build(to.getPerspectiva()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		objetivoRepository.save(entity);
		
	}
}
