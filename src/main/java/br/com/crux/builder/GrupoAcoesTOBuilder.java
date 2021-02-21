package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetOficinasCmd;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.entity.Oficinas;
import br.com.crux.to.GrupoAcoesSimlesTO;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class GrupoAcoesTOBuilder {

	@Autowired private OficinasTOBuilder atividadeBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetOficinasCmd getAtividadeCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private AcaoTOBuilder acaoTOBuilder;
	
	
	public GrupoAcoes build(GrupoAcoesTO p) {
		GrupoAcoes retorno = new GrupoAcoes();

		BeanUtils.copyProperties(p, retorno);
		
		Optional.ofNullable(p.getFuncionarioAnalise()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioAnalise(funcionario);
			}
		});

		Optional.ofNullable(p.getAtividade()).ifPresent(atv -> {
			Oficinas atividade = getAtividadeCmd.getById(atv.getId());
			retorno.setAtividade(atividade);
		});
		
		Optional.ofNullable(p.getAcoes()).ifPresent(acaoTO -> {
			retorno.setAcoes(acaoTOBuilder.buildTOAll(acaoTO));
		});

		return retorno;
	}

	public GrupoAcoesTO buildTO(GrupoAcoes p) {
		GrupoAcoesTO retorno = new GrupoAcoesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setFuncionarioAnalise(funcionarioTOBuilder.buildTO(p.getFuncionarioAnalise()));
		
		if(Objects.nonNull(p.getId())) {
			if(!p.getAcoes().isEmpty()) {
				retorno.setAcoes(acaoTOBuilder.buildAll(p.getAcoes()));
			}
		}

		return retorno;
	}

	public GrupoAcoesTO buildSemAcao(GrupoAcoes p) {
		GrupoAcoesTO retorno = new GrupoAcoesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setFuncionarioAnalise(funcionarioTOBuilder.buildTO(p.getFuncionarioAnalise()));

		return retorno;
	}

	

	public GrupoAcoesSimlesTO buildSimplesTO(GrupoAcoes p) {
		GrupoAcoesSimlesTO retorno = new GrupoAcoesSimlesTO();
		
		if (Objects.isNull(p)) {return retorno;}
		
		BeanUtils.copyProperties(p, retorno);
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));

		return retorno;
	}
	
	public List<GrupoAcoesTO> buildAll(List<GrupoAcoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<GrupoAcoes> buildTOAll(List<GrupoAcoesTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
}
