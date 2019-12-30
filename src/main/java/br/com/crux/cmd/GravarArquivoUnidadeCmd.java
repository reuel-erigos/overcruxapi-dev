package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Unidade;

@Component
public class GravarArquivoUnidadeCmd {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired ArquivoBuilder arquivoBuilder;
	@Autowired ArquivoRepository arquivoRepository;
	@Autowired UnidadeRepository unidadeRepository;
	@Autowired GetUnidadePorIdCmd getUnidadePorIdCmd;

	public void gravar(MultipartFile file, Long idUnidade) {
		Unidade unidade = getUnidadePorIdCmd.getUnidade(idUnidade);
		Arquivo arquivo = arquivoBuilder.build(file);

		Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		arquivo.setUsuarioAlteracao(idUsuarioLogado);

		Arquivo arquivoSalvo = arquivoRepository.save(arquivo);
		arquivo.setIdArquivo(arquivoSalvo.getIdArquivo());

		unidade.setIdArquivo(arquivo.getIdArquivo());
		unidadeRepository.save(unidade);

	}
}
