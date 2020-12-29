package br.com.crux.cmd.relatorios.secretaria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.DeclaracaoBeneficiarioTOBuilder;
import br.com.crux.dao.relatorios.DeclaracaoBeneficiarioDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.beneficiarios.DeclaracaoBeneficiarioTO;

@Component
public class GerarRelatorioDeclaracaoBeneficiarioCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private DeclaracaoBeneficiarioDao dao;
	@Autowired private DeclaracaoBeneficiarioTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "Declaracao_Beneficiario";
		String[] path = {"casa_azul", "secretaria", "declaracao_beneficiario"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<DeclaracaoBeneficiarioTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
