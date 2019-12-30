package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.entity.PrestacoesConta;
import br.com.crux.to.PrestacoesContaTO;

@Component
public class PrestacoesContaTOBuilder {

	@Autowired GetProgramaCmd getProgramaCmd;
	@Autowired GetProjetoCmd getProjetoCmd;
	@Autowired ProgramaTOBuilder programaTOBuilder;
	@Autowired ProjetoTOBuilder projetoTOBuilder;

	public PrestacoesConta build(PrestacoesContaTO to) {
		PrestacoesConta retorno = new PrestacoesConta();

		BeanUtils.copyProperties(to, retorno);

		Optional.ofNullable(to.getPrograma()).ifPresent(p -> retorno.setPrograma(getProgramaCmd.getById(to.getPrograma().getId())));
		Optional.ofNullable(to.getProjeto()).ifPresent(p -> retorno.setProjeto(getProjetoCmd.getById(to.getProjeto().getId())));

		return retorno;
	}

	public PrestacoesContaTO buildTO(PrestacoesConta entity) {
		PrestacoesContaTO to = new PrestacoesContaTO();

		BeanUtils.copyProperties(entity, to);

		to.setPrograma(programaTOBuilder.buildTO(entity.getPrograma()));
		to.setProjeto(projetoTOBuilder.buildTO(entity.getProjeto()));

		return to;

	}

	public List<PrestacoesContaTO> buildAll(List<PrestacoesConta> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
