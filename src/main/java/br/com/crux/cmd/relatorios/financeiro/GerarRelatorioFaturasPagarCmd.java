package br.com.crux.cmd.relatorios.financeiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.financeiro.FaturasPagarTO;

@Component
public class GerarRelatorioFaturasPagarCmd {
	
	@Autowired private GeradorRelatorio geradorRelatorio;
	
	public byte[] gerar(List<FaturasPagarTO> dados, String mimeType)  {
		try {
			String nomeRelatorio = "FaturasPagar";
			String[] path = {"casa_azul", "financeiro", "faturas_pagar"};
			
			Map<String, Object> parametros = new HashMap<>();
			
			return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}	
}
