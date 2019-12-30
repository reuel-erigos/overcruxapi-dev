package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class GetArquivoInstituicaoCmd {

	@Autowired GetInstituicaoCmd getInstituicaoCmd;
	@Autowired ArquivoRepository arquivoRepository;

	public byte[] get(Long idInstituicao) {
		if (Objects.isNull(idInstituicao)) {
			throw new ParametroNaoInformadoException("Erro ao buscar o arquivo, parâmetro não informado no resource.");
		}

		Instituicao instituicao = getInstituicaoCmd.getById(idInstituicao);

		if (instituicao.getIdArquivo() == null) {
			return null;
		}

		Optional<Arquivo> arquivo = arquivoRepository.findById(instituicao.getIdArquivo());
		if (!arquivo.isPresent()) {
			return null;
		}

		return arquivo.get().getBlob();
	}
}
