package br.com.crux.cmd.relatorios.financeiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;

@Component
public class GerarRelatorioMovimentacaoContabilCmd {
	
	@Autowired private GeradorRelatorio geradorRelatorio;
	
	public byte[] gerar(List<MovimentacaoContabilTO> dados, String mimeType)  {
		try {
			String nomeRelatorio = "NormativaPagamentos";
			String[] path = {"casa_azul", "financeiro", "normativa_pagamento"};
			
			Map<String, Object> parametros = new HashMap<>();
			
			return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}	
}
