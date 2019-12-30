package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoCmd {

	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GravarArquivoUnidadeCmd gravarArquivoUnidadeCmd;
	@Autowired private GetArquivoUnidadeCmd getArquivoUnidadeCmd;
	@Autowired private AlterarArquivoUnidadeCmd alterarArquivoUnidadeCmd;
	
	@Autowired private GravarArquivoInstituicaoCmd gravarArquivoInstituicaoCmd;
	@Autowired private GetArquivoInstituicaoCmd getArquivoInstituicaoCmd;
	@Autowired private AlterarArquivoInstituicaoCmd alterarArquivoInstituicaoCmd;

	public void salvar(MultipartFile file) {
		gravarArquivoUnidadeCmd.gravar(file, getUnidadeLogadaCmd.get().getId());
	}

	// Unidade
	public void salvarComIdUnidade(MultipartFile file, Long idUnidade) {
		gravarArquivoUnidadeCmd.gravar(file, idUnidade);
	}

	public byte[] getArquivoPorUnidade(Long idUnidade) {
		return getArquivoUnidadeCmd.get(idUnidade);
	}
	
	public void alterarArquivoUnidade(MultipartFile file, Long idUnidade) {
		alterarArquivoUnidadeCmd.alterar(file, idUnidade);
	}
	
	// Instituição
	public void salvarComIdInstituicao(MultipartFile file, Long id) {
		gravarArquivoInstituicaoCmd.gravar(file, id);
	}

	public byte[] getArquivoPorInstituicao(Long id) {
		return getArquivoInstituicaoCmd.get(id);
	}
	
	public void alterarArquivoInstituicao(MultipartFile file, Long id) {
		alterarArquivoInstituicaoCmd.alterar(file, id);
	}
	

}
