package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetTributoMovimentacaoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.FaturaTO;

@Component
public class FaturaTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private TributoMovimentacaoTOBuilder tributoMovimentacaoTOBuilder;
	@Autowired private GetTributoMovimentacaoCmd getTributoMovimentacaoCmd ;
	

	public FaturaTO buildTO(Fatura entity) {
		FaturaTO to = new FaturaTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		to.setIdMovimentacao(entity.getIdMovimentacao());
		to.setTributoMovimentacao(tributoMovimentacaoTOBuilder.buildTO(entity.getTributoMovimentacao()));
		return to;
	}

	public List<FaturaTO> buildAll(List<Fatura> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public Fatura build(Movimentacoes movimentacoes, FaturaTO faturaTO) {
		Fatura entity = new Fatura();

		BeanUtils.copyProperties(faturaTO, entity);
		entity.setIdMovimentacao(movimentacoes.getId());
		
		if(Objects.nonNull(faturaTO.getTributoMovimentacao()) && Objects.nonNull(faturaTO.getTributoMovimentacao().getTributo())) {
			entity.setTributoMovimentacao(getTributoMovimentacaoCmd.getByIdMovimentacaoAndIdTributo(movimentacoes.getId(), faturaTO.getTributoMovimentacao().getTributo().getId()));
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
