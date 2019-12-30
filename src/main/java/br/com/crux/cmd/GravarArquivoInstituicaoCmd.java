package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Instituicao;

@Component
public class GravarArquivoInstituicaoCmd {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired ArquivoBuilder arquivoBuilder;
	@Autowired ArquivoRepository arquivoRepository;
	@Autowired InstituicaoRepository repository;
	@Autowired GetInstituicaoCmd getInstituicaoCmd;

	public void gravar(MultipartFile file, Long idInstituicao) {
		Instituicao instituicao = getInstituicaoCmd.getById(idInstituicao);
		Arquivo arquivo = arquivoBuilder.build(file);

		arquivo.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Arquivo arquivoSalvo = arquivoRepository.save(arquivo);
		arquivo.setIdArquivo(arquivoSalvo.getIdArquivo());

		instituicao.setIdArquivo(arquivo.getIdArquivo());
		repository.save(instituicao);

	}
}
