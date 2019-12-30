package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ClassificadorSituacaoImovel;
import br.com.crux.to.UnidadeTO;

@Component
public class UnidadeTOBuilder {

	@Autowired private InstituicaoTOBuilder instituicaoTOBuilder;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;

	public Unidade build(UnidadeTO to) {
		Unidade unidade = new Unidade();

		unidade.setIdUnidade(to.getIdUnidade());
		unidade.setSiglaUnidade(to.getSiglaUnidade());
		unidade.setNomeUnidade(to.getNomeUnidade());
		unidade.setEndereco(to.getEndereco());
		unidade.setTelefone(to.getTelefone());
		unidade.setDescricaoSituacaoImovel(to.getDescricaoSituacaoImovel());
		unidade.setDescricaoEstruturaFisicaImovel(to.getDescricaoEstruturaFisicaImovel());
		unidade.setUsuarioAlteracao(to.getUsuarioAlteracao());
		unidade.setVisao(to.getVisao());
		unidade.setMissao(to.getMissao());
		unidade.setEmail(to.getEmail());
		unidade.setCep(to.getCep());
		unidade.setBairro(to.getBairro());
		unidade.setUf(to.getUf());
		unidade.setCelular(to.getCelular());
		unidade.setTipoUnidade(to.getTipoUnidade());
		Optional.ofNullable(to.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			unidade.setClassificacaoSituacaoImovel(ClassificadorSituacaoImovel.getPorTipo(classificador));
		});

		unidade.setNomeFantasia(to.getNomeFantasia());
		unidade.setCnpj(to.getCnpj());
		unidade.setInscricaoEstadual(to.getInscricaoEstadual());
		unidade.setInscricaoMunicipal(to.getInscricaoMunicipal());
		unidade.setHomePage(to.getHomePage());
		unidade.setCidade(to.getCidade());

		if (Objects.nonNull(to.getInstituicao()) && Objects.nonNull(to.getInstituicao().getId())) {
			
			Instituicao instituicao = getInstituicaoCmd.getById(to.getInstituicao().getId());
			unidade.setInstituicao(instituicao);
		}

		unidade.setIdArquivo(to.getArquivo());
		
		unidade.setNumeroCas(to.getNumeroCas());
		unidade.setNumeroCdca(to.getNumeroCdca());
		unidade.setDataVigenciaCdca(to.getDataVigenciaCdca());
		unidade.setNumeroCnas(to.getNumeroCnas());
		unidade.setDataVigenciaCas(to.getDataVigenciaCas());

		return unidade;
	}

	public UnidadeTO buildTO(Unidade to) {
		UnidadeTO unidade = new UnidadeTO();

		if (Objects.isNull(to)) {
			return unidade;
		}

		unidade.setIdUnidade(to.getIdUnidade());
		unidade.setSiglaUnidade(to.getSiglaUnidade());
		unidade.setNomeUnidade(to.getNomeUnidade());
		unidade.setEndereco(to.getEndereco());
		unidade.setTelefone(to.getTelefone());
		unidade.setDescricaoSituacaoImovel(to.getDescricaoSituacaoImovel());
		unidade.setDescricaoEstruturaFisicaImovel(to.getDescricaoEstruturaFisicaImovel());
		unidade.setUsuarioAlteracao(to.getUsuarioAlteracao());
		unidade.setVisao(to.getVisao());
		unidade.setMissao(to.getMissao());
		unidade.setEmail(to.getEmail());
		unidade.setCep(to.getCep());
		unidade.setBairro(to.getBairro());
		unidade.setUf(to.getUf());
		unidade.setCelular(to.getCelular());
		unidade.setTipoUnidade(to.getTipoUnidade());
		Optional.ofNullable(to.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			unidade.setClassificacaoSituacaoImovel(classificador.getTipo());
		});

		unidade.setNomeFantasia(to.getNomeFantasia());
		unidade.setCnpj(to.getCnpj());
		unidade.setInscricaoEstadual(to.getInscricaoEstadual());
		unidade.setInscricaoMunicipal(to.getInscricaoMunicipal());
		unidade.setHomePage(to.getHomePage());
		unidade.setCidade(to.getCidade());
		unidade.setArquivo(to.getIdArquivo());

		unidade.setInstituicao(instituicaoTOBuilder.buildTO(to.getInstituicao()));
		
		unidade.setNumeroCas(to.getNumeroCas());
		unidade.setNumeroCdca(to.getNumeroCdca());
		unidade.setDataVigenciaCdca(to.getDataVigenciaCdca());
		unidade.setNumeroCnas(to.getNumeroCnas());
		unidade.setDataVigenciaCas(to.getDataVigenciaCas());

		return unidade;
	}

	public List<UnidadeTO> buildAllTO(List<Unidade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public UnidadeTO buildTOComUnidadeLogada(Unidade unidade) {
		UnidadeTO unidadeTO = buildTO(unidade);
		unidadeTO.setUnidadeLogada(Boolean.TRUE);
		return unidadeTO;
		
	}

}
