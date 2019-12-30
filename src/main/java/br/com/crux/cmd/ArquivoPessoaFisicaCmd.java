package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoPessoaFisicaCmd {

	@Autowired private SalvarArquivoPessoaFisicaCmd salvarArquivoPessoaFisicaCmd;
	@Autowired private AlterarArquivoPessoaFisicaCmd alterarArquivoPessoaFisicaCmd;
	@Autowired private GetArquivoPessoaFisicaCmd getArquivoPessoaFisicaCmd;

	public void salvar(MultipartFile file, Long idPessoaFisica) {
		salvarArquivoPessoaFisicaCmd.salvar(file, idPessoaFisica);
	}


	public void alterar(MultipartFile file, Long idPessoaFisica) {
		alterarArquivoPessoaFisicaCmd.alterar(file, idPessoaFisica);
		
	}
	
	public byte[] getPorPessoa(Long idPessoaFisica) {
		return getArquivoPessoaFisicaCmd.getPorPessoa(idPessoaFisica);
	}
}
