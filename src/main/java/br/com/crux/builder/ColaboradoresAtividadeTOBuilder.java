package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.cmd.GetCargosCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.ColaboradoresAtividade;
import br.com.crux.entity.Funcionario;
import br.com.crux.to.ColaboradoresAtividadeTO;

@Component
public class ColaboradoresAtividadeTOBuilder {

	@Autowired GetAtividadeCmd getAtividadeCmd;
	@Autowired GetCargosCmd getCargosCmd;
	@Autowired GetFuncionarioCmd getFuncionarioCmd;
	@Autowired AtividadesTOBuilder atividadesTOBuilder;
	@Autowired CargosTOBuilder cargosTOBuilder;
	@Autowired FuncionarioTOBuilder funcionarioTOBuilder;

	private ColaboradoresAtividade baseBuild(ColaboradoresAtividadeTO p) {
		ColaboradoresAtividade entity = new ColaboradoresAtividade();

		entity.setIdColaboradorAtividade(p.getId());
		entity.setDtEntradaAtividade(p.getDtEntradaAtividade());
		entity.setDtSaidaAtividade(p.getDtSaidaAtividade());
		entity.setIdAtividade(p.getIdAtividade());
		
		Optional.ofNullable(p.getCargo()).ifPresent(c -> {
			if (Objects.nonNull(c.getId())) {
				Cargo cargo = getCargosCmd.getById(c.getId());
				entity.setCargo(cargo);
			}
		});

		Optional.ofNullable(p.getFuncionario()).ifPresent(f -> {
			if (Objects.nonNull(f.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(f.getId());
				entity.setFuncionario(funcionario);
			}
				
		});

		entity.setUsuariosSistema(p.getUsuariosSistema());

		return entity;

	}

	public ColaboradoresAtividade build(ColaboradoresAtividadeTO p) {
		ColaboradoresAtividade entity = baseBuild(p);
		return entity;
	}

	public ColaboradoresAtividade buildComIdAtividade(ColaboradoresAtividadeTO to, Long idAtividade) {
		ColaboradoresAtividade entity = baseBuild(to);
		entity.setIdAtividade(idAtividade);
		return entity;
	}

	public ColaboradoresAtividadeTO buildTO(ColaboradoresAtividade p) {

		ColaboradoresAtividadeTO to = new ColaboradoresAtividadeTO();

		if (Objects.isNull(p)) {
			return to;
		}

		to.setId(p.getIdColaboradorAtividade());
		to.setDtEntradaAtividade(p.getDtEntradaAtividade());
		to.setDtSaidaAtividade(p.getDtSaidaAtividade());
		to.setCargo(cargosTOBuilder.buildTO(p.getCargo()));
		to.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));
		to.setUsuariosSistema(p.getUsuariosSistema());

		return to;
	}

	public List<ColaboradoresAtividadeTO> buildAll(List<ColaboradoresAtividade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
