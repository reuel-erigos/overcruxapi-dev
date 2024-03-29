package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.AtividadesAluno;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.AtividadesAlunoTO;
import br.com.crux.to.OficinasTO;

@Component
public class AtividadesAlunoTOBuilder {

	@Autowired private OficinasTOBuilder atividadeBuilder;
	@Autowired private AlunoTOBuilder alunoBuilder;

	public AtividadesAluno build(AtividadesAlunoTO p) {
		AtividadesAluno retorno = new AtividadesAluno();

		retorno.setId(p.getId());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setDataInicioAtividade(p.getDataInicioAtividade());
		retorno.setDataDesvinculacao(p.getDataDesvinculacao());
		retorno.setDataCadastroAtividade(p.getDataCadastroAtividade());
		retorno.setDataAlteracaoAtividade(p.getDataAlteracaoAtividade());
		retorno.setAluno(alunoBuilder.build(p.getAluno()));
		retorno.setAtividade(atividadeBuilder.build(p.getAtividade()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AtividadesAlunoTO buildTO(AtividadesAluno p) {
		AtividadesAlunoTO retorno = new AtividadesAlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setDataInicioAtividade(p.getDataInicioAtividade());
		retorno.setDataDesvinculacao(p.getDataDesvinculacao());
		retorno.setDataCadastroAtividade(p.getDataCadastroAtividade());
		retorno.setDataAlteracaoAtividade(p.getDataAlteracaoAtividade());
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AtividadesAlunoTO buildTOAtividadeUniforme(AtividadesAluno p) {
		AtividadesAlunoTO retorno = new AtividadesAlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setDataInicioAtividade(p.getDataInicioAtividade());
		retorno.setDataDesvinculacao(p.getDataDesvinculacao());
		retorno.setDataCadastroAtividade(p.getDataCadastroAtividade());
		retorno.setDataAlteracaoAtividade(p.getDataAlteracaoAtividade());
		retorno.setAluno(new AlunoTO());
		retorno.getAluno().setId(p.getAluno().getId());
		retorno.setAtividade(new OficinasTO());
		retorno.getAtividade().setId(p.getAtividade().getId());
		retorno.getAtividade().setDescricao(p.getAtividade().getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	
	public List<AtividadesAlunoTO> buildAll(List<AtividadesAluno> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
