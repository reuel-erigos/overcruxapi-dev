package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresProjetoCmd;
import br.com.crux.cmd.GetComposicaoRhProjetoCmd;
import br.com.crux.cmd.GetContasCentrosCustoCmd;
import br.com.crux.cmd.GetParceriasProjetoCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetosUnidadeCmd;
import br.com.crux.dao.dto.ComboProjetoDTO;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ComboProjetoTO;
import br.com.crux.to.ProjetoTO;

@Component
public class ProjetoTOBuilder {

	@Autowired private GetProjetosUnidadeCmd getProjetosUnidadeCmd;
	@Autowired private GetColaboradoresProjetoCmd getColaboradoresProjetoCmd;
	@Autowired private GetParceriasProjetoCmd getParceriasProjetoCmd;
	@Autowired private GetComposicaoRhProjetoCmd getComposicaoRhProjetoCmd;
	@Autowired private GetContasCentrosCustoCmd getContasCentrosCustoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private ProgramaTOBuilder programaTOBuilder;

	public Projeto build(ProjetoTO p) {
		Projeto retorno = new Projeto();

		BeanUtils.copyProperties(p, retorno);
		
		Optional.ofNullable(p.getPrograma()).ifPresent(programa -> {
			if (Objects.nonNull(programa.getId())) {
				Programa entity = getProgramaCmd.getById(programa.getId());
				retorno.setPrograma(entity);
			}
		});

		return retorno;
	}

	public ProjetoTO buildTO(Projeto p) {
		ProjetoTO retorno = new ProjetoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);

		retorno.setUnidades(getProjetosUnidadeCmd.getUnidadesTOByIdProjeto(p.getId()));
		retorno.setColaboradoresProjeto((getColaboradoresProjetoCmd.getColaboradoresProjetoTOByProjeto(p)));
		retorno.setParceriasProjeto(getParceriasProjetoCmd.getParceriasProjetoTOByProjeto(p));
		retorno.setComposicaoRhProjeto(getComposicaoRhProjetoCmd.getComposicaoRhProjetoByProjeto(p));
		retorno.setContasCentrosCusto(getContasCentrosCustoCmd.getTOPorProjeto(p));

		Optional.ofNullable(p.getPrograma()).ifPresent(programa -> {
			retorno.setPrograma(programaTOBuilder.buildTO(programa));
		});
		
		return retorno;
	}

	public ProjetoTO buildTOEnxuto(Projeto projeto) {
		if (Objects.isNull(projeto)) {return null;}
		ProjetoTO to = new ProjetoTO();
		to.setId(projeto.getId());
		to.setNome(projeto.getNome());
		return to;
	}
	
	public List<ProjetoTO> buildAll(List<Projeto> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	
	public List<ComboProjetoTO> buildAllCombo(List<ComboProjetoDTO> dtos) {
		return dtos.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());
	}

	public ComboProjetoTO buildTOCombo(ComboProjetoDTO dto) {
		ComboProjetoTO to = new ComboProjetoTO();
		if (Objects.isNull(dto)) {return to;}
		to.setId(dto.getId());
		to.setNome(dto.getNome());
		return to;
	}
}
