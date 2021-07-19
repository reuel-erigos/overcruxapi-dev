package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.MovimentacoesContabeis;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.MovimentacoesContabeisTO;

@Component
public class MovimentacoesContabeisTOBuilder {

	@Autowired private ProgramaTOBuilder programaBuilder;
	@Autowired private ProjetoTOBuilder projetoBuilder;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;

	
	public MovimentacoesContabeis build(MovimentacoesContabeisTO to) {
		MovimentacoesContabeis entity = new MovimentacoesContabeis();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getPrograma01()) && Objects.nonNull(to.getPrograma01().getId())) {
			Programa registro = getProgramaCmd.getById(to.getPrograma01().getId());
			entity.setPrograma01(registro);
		}
		if (Objects.nonNull(to.getProjeto01()) && Objects.nonNull(to.getProjeto01().getId())) {
			Projeto registro = getProjetoCmd.getById(to.getProjeto01().getId());
			entity.setProjeto01(registro);
		}

		if (Objects.nonNull(to.getCategoriaOrigem01()) && Objects.nonNull(to.getCategoriaOrigem01().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(to.getCategoriaOrigem01().getId());
			entity.setCategoriaOrigem01(categoria);
		}

		if (Objects.nonNull(to.getCategoriaDestino01()) && Objects.nonNull(to.getCategoriaDestino01().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(to.getCategoriaDestino01().getId());
			entity.setCategoriaDestino01(categoria);
		}
		
		if (Objects.nonNull(to.getPrograma02()) && Objects.nonNull(to.getPrograma02().getId())) {
			Programa registro = getProgramaCmd.getById(to.getPrograma02().getId());
			entity.setPrograma02(registro);
		}
		if (Objects.nonNull(to.getProjeto02()) && Objects.nonNull(to.getProjeto02().getId())) {
			Projeto registro = getProjetoCmd.getById(to.getProjeto02().getId());
			entity.setProjeto02(registro);
		}

		if (Objects.nonNull(to.getCategoriaOrigem02()) && Objects.nonNull(to.getCategoriaOrigem02().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(to.getCategoriaOrigem02().getId());
			entity.setCategoriaOrigem02(categoria);
		}

		if (Objects.nonNull(to.getCategoriaDestino02()) && Objects.nonNull(to.getCategoriaDestino02().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(to.getCategoriaDestino02().getId());
			entity.setCategoriaDestino02(categoria);
		}
		
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

	public MovimentacoesContabeisTO buildTO(MovimentacoesContabeis entity) {
		MovimentacoesContabeisTO to = new MovimentacoesContabeisTO();

		if (Objects.isNull(entity)) {return to;}
		BeanUtils.copyProperties(entity, to);
		
		if(Objects.nonNull(entity.getPrograma01())) {
			to.setPrograma01(programaBuilder.buildTO(entity.getPrograma01()));
		}

		if(Objects.nonNull(entity.getProjeto01())) {
			to.setProjeto01(projetoBuilder.buildTO(entity.getProjeto01()));
		}

		if(Objects.nonNull(entity.getCategoriaOrigem01())) {
			to.setCategoriaOrigem01(categoriasContabeisTOBuilder.buildTO(entity.getCategoriaOrigem01()));
		}

		if(Objects.nonNull(entity.getCategoriaDestino01())) {
			to.setCategoriaDestino01(categoriasContabeisTOBuilder.buildTO(entity.getCategoriaDestino01()));
		}
		
		if(Objects.nonNull(entity.getPrograma02())) {
			to.setPrograma02(programaBuilder.buildTO(entity.getPrograma02()));
		}

		if(Objects.nonNull(entity.getProjeto02())) {
			to.setProjeto02(projetoBuilder.buildTO(entity.getProjeto02()));
		}
		
		if(Objects.nonNull(entity.getCategoriaOrigem02())) {
			to.setCategoriaOrigem01(categoriasContabeisTOBuilder.buildTO(entity.getCategoriaOrigem02()));
		}

		if(Objects.nonNull(entity.getCategoriaDestino02())) {
			to.setCategoriaDestino02(categoriasContabeisTOBuilder.buildTO(entity.getCategoriaDestino02()));
		}

		return to;
	}

	public List<MovimentacoesContabeisTO> buildAll(List<MovimentacoesContabeis> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<MovimentacoesContabeis> buildTOAll(List<MovimentacoesContabeisTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}

}
