package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.ExportacaoDadosAlunoDao;
import br.com.crux.dao.dto.ExportarDadosBeneficiarioDTO;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.exportacao.GrupoDadosExportar;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;


@Component
public class ExportacaoDadosAlunoExcelFileExporter {
	
	@Autowired private ExportacaoDadosAlunoDao exportacaoDadosAlunoDao;
	
	private String[] origemBeneficiosAluno =  {"Entidade Social", "Data Encaminhamento", "Descrição Encaminhamento"};
	
	public byte[] gerar(ListaCompletaDadosExportar listaCompletaDadosExportar) {
		
		ByteArrayInputStream stream = gerarFileExcel(listaCompletaDadosExportar);
        byte[] targetArray = new byte[stream.available()];
        try {
			stream.read(targetArray);
		} catch (IOException e) {
			throw new NegocioException	(e.getMessage());
		}
        return targetArray;
	}
	
	private ByteArrayInputStream gerarFileExcel(ListaCompletaDadosExportar listaCompletaDadosExportar) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Aluno_Familiar");
			
			List<GrupoDadosExportar> grupoAluno    = listaCompletaDadosExportar.getExportarDados().getDados().stream().filter(p -> p.getEntidade().equals("aluno")).collect(Collectors.toList());
			List<GrupoDadosExportar> grupoFamiliar = listaCompletaDadosExportar.getExportarDados().getDados().stream().filter(p -> p.getEntidade().equals("familiar")).collect(Collectors.toList());
			
		
			Row row = sheet.createRow(0);
			
			AtomicInteger indexColuna = new AtomicInteger(0);
			grupoAluno.stream().forEach(grupo -> {
				
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
				                
				    if(grupo.getNomeGrupo().equals("outras_informacoes") && coluna.getNome().equals("origemBeneficio")) {
				    	for (String valor : origemBeneficiosAluno) {
				    		Cell cell = row.createCell(indexColuna.getAndIncrement());
				    		cell.setCellValue(valor);
				    		cell.setCellStyle(headerCellStyle);
						}
				    } else {
				    	Cell cell = row.createCell(indexColuna.getAndIncrement());
				    	cell.setCellValue(coluna.getDescricao());
				    	cell.setCellStyle(headerCellStyle);
				    }
				});				
			});
			

			grupoFamiliar.stream().forEach(grupo -> {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.LIME.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
					Cell cell = row.createCell(indexColuna.getAndIncrement());
					cell.setCellValue(coluna.getDescricao());
					cell.setCellStyle(headerCellStyle);
				});				
			});
			
			
			AtomicInteger linha = new AtomicInteger(1);
			listaCompletaDadosExportar.getListaDadosExportacao().forEach( registro -> {
	        	List<ExportarDadosBeneficiarioDTO> dadosExportarBeneficiario = exportacaoDadosAlunoDao.getDadosExportarBeneficiario(registro.getIdAluno());
	        	
	        	List<GrupoDadosExportar> ga = grupoAluno.stream().collect(Collectors.toList());
	        	List<GrupoDadosExportar> gf = grupoFamiliar.stream().collect(Collectors.toList());
	        	
	        	dadosExportarBeneficiario.forEach( dadosTO -> {
		        	AtomicInteger indexDados = new AtomicInteger(0);
		        	Row dataRow = sheet.createRow(linha.getAndIncrement());
		        	
	        		//Dados do Aluno
	          	  	preencherDadosPessoaisAluno(ga,indexDados, dataRow, dadosTO);
	          	    preencherAdmissaoAluno(ga, indexDados, dataRow, dadosTO);
	          	    preencherEscolaridadeAluno(ga, indexDados, dataRow, dadosTO);
	          	    preencherDocumentosAluno(ga, indexDados, dataRow, dadosTO);
	          	    preencherOutrasInformacoesAluno(ga, indexDados, dataRow, dadosTO);
	          	    
	          	    //Dados do familiar
          	    	preencherDadosPessoaisFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherParentescoFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherResponsavelFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherEscolaridadeFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherDocumentosFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherDadosProfissionaisFamiliar(gf,indexDados, dataRow, dadosTO);
          	    	preencherOutrasInformacoesFamiliar(gf,indexDados, dataRow, dadosTO);
	          	    
	        	});
			});
	        
	        
	        for (int i = 0; i < indexColuna.get(); i++) {
	        	sheet.autoSizeColumn(i);
			}
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProvisionamentoNaoGeradoException("Erro ao exportar os dados do aluno: " + ex.getMessage());
		}
	}

	
	
	private void preencherOutrasInformacoesFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTipoEscolaFamiliar()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("turnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTurnoFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEscolaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nomeCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCursoEscolaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("periodoCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPeriodoEscolaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSerieEscolaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRegiaoAdministrativaEscolaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPossuiDeficienteFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoDeficienciaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoProblemaSaudeFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoMedicamentosControladosFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoAtendidoOrgaoRedeFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getBeneficiosFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoRedeApSocRelevFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoRedeApoioSocialFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  if(Objects.nonNull(dadosTO.getValorBeneficiosFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getValorBeneficiosFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getOrigemRendaFamiliar()).orElse(""));
            	  }

			});		
    	}	

	}

	private void preencherDadosProfissionaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_profissionais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("profissao")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getDescricaoProfissaoFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDescricaoProfissaoFamiliar());           	  
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("empresa")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getNomeEmpresaTrabalhoFamiliar()) ) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getNomeEmpresaTrabalhoFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("possuiBolsaFamilia")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getBeneficioBolsaFamiliaFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getBeneficioBolsaFamiliaFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("valorBolsaFamilia")) {
            		  if(Objects.nonNull(dadosTO.getValorBolsaFamiliaFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getValorBolsaFamiliaFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("valorRenda")) {
            		  if(Objects.nonNull(dadosTO.getValorRendaFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getValorRendaFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("telefoneComercial")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getFoneComercialFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getFoneComercialFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("situacaoTrabalho")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getDescricaoSituacaoTrabalhoFamiliar()) ) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDescricaoSituacaoTrabalhoFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("motivoNaoTrabalhar")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getDescricaoMotivoNaoTrabFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDescricaoMotivoNaoTrabFamiliar());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
			});		
    	}	

	}

	private void preencherDocumentosFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
           			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCpfFamiliar()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNisFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPispasepFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getIdentidadeFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getOrgaoExpedidorCiFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfCiFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(dadosTO.getDataEmissaoCiFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataEmissaoCiFamiliar()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTituloEleitorFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getZonaTituloFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSessaoTituloFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCertificadoReservistaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRegiaoMilitarReservistaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfReservistaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCnhFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCategoriaCnhFamiliar()).orElse(""));
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(dadosTO.getDataVencimentoCnhFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataVencimentoCnhFamiliar()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCarteiraTrabalhoFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSerieCarteiraTrabalhoFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfCarteiraTrabalhoFamiliar()).orElse(""));
            	  }
			});		
    	}	
		
	}

	private void preencherEscolaridadeFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nivel")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNivelEscolaridadeFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(Objects.nonNull(dadosTO.getGrauInscrucaoFamiliar())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getGrauInscrucaoFamiliar());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
			});		
    	}
	}

	private void preencherResponsavelFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("responsavel")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataVinculacao")) {
                	  if(Objects.nonNull(dadosTO.getDataVinculacaoFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataVinculacaoFamiliar()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesvinculacao")) {
                	  if(Objects.nonNull(dadosTO.getDataDesvinculacaoFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataDesvinculacaoFamiliar()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("beneficiarioMesmoEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getMesmoEnderecoRespFamiliar()).orElse(""));
                  }

                  if(coluna.getNome().equals("autorizaLevarBuscar")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTransportaAlunoFamiliar()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("temTutela")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTutelaAlunoFamiliar()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("responsavelFinanceiro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRespFinanceiroAlunoFamiliar()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaograuParentescoResponsavelFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoDesligamentoResponsavelFamiliar()).orElse(""));
                  }
			});		
    	}
	}

	private void preencherParentescoFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("parentesco")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataCadastro")) {
                	  if(Objects.nonNull(dadosTO.getDataCadastroFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataCadastroFamiliar()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesligamento")) {
                	  if(Objects.nonNull(dadosTO.getDatDesligamentoFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDatDesligamentoFamiliar()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("situacaoParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSituacaoParentescoFamiliar()).orElse(""));
                  }

                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoGrauParentescoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoDesligamentoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("outrasInformacoes")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoOutrasInformacoesFamiliar()).orElse(""));
                  }
			});		
    	}

	}

	private void preencherDadosPessoaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomeFamiliar()).orElse(""));
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(StringUtils.isNotEmpty(dadosTO.getDataNascimentoFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDataNascimentoFamiliar());
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNaturalidadeFamiliar()).orElse(""));
                  }

                  if(coluna.getNome().equals("ufNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfNascimentoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSexoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRacaFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomeMaeFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomaPaiFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEstadoCivilFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTipoSangueFamiliar()).orElse(""));
                  }
                                    
                  if(coluna.getNome().equals("cep")) {
                	  if(StringUtils.isNotEmpty(dadosTO.getCepFamiliar())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCepFamiliar()).orElse(null));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEnderecoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCidadeFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getBairroFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfEnderecoFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPontoReferenciaFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getFoneResidencialFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getFoneCelularFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEmailFamiliar()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getAutorizaEmailFamiliar()).orElse(""));
                  }
			});		
    	}
	}

	
	
	private void preencherDadosPessoaisAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomeAluno()).orElse(""));
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(StringUtils.isNotEmpty(dadosTO.getDataNascimentoAluno())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDataNascimentoAluno());
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNaturalidadeAluno()).orElse(""));
                  }

                  if(coluna.getNome().equals("uf")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfNascimentoAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSexoAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRacaAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomeMaeAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNomePaiAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEstadoCivilAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTipoSangueAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cep")) {
                	  if(StringUtils.isNotEmpty(dadosTO.getCepAluno())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCepAluno()).orElse(null));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEnderecoAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCidadeAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getBairroAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfEnderecoAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPontoReferenciaAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getFoneResidencialAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getFoneCelularAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEmailAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getStAutorizaEmailAluno()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("foneRecado")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getFoneRecadoAluno()).orElse(""));
                  }                  
			});		
    	}	
	}
	
	private void preencherAdmissaoAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("admissao")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
	        	  if(coluna.getNome().equals("matricula")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getMatriculaAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("unidade")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUnidadeAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("programa")) {
	        		  if(StringUtils.isNotEmpty(dadosTO.getProgramaAluno())){
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getProgramaAluno()).orElse(""));
	        		  } else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }

	        	  if(coluna.getNome().equals("projeto")) {
	        		  if(StringUtils.isNotEmpty(dadosTO.getProjetoAluno())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getProjetoAluno()).orElse(""));
	        		  } else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
	        	  
	        	  if(coluna.getNome().equals("dataEntrada")) {
	        		  if(Objects.nonNull(dadosTO.getDataEntradaAluno())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataEntradaAluno()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("dataDesligamento")) {
	        		  if(Objects.nonNull(dadosTO.getDataDesligamentoAluno())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataDesligamentoAluno()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("moraComOsPais")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getMoraPaisAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("paisSaoCasados")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPaisCasadosAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("alunoPublicoPrioritario")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPublicoPrioritarioAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("descPessoaBuscaAlunoEscola")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getBuscaEscolaAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("desligamento")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDesligamentoAluno()).orElse(""));            	  
	        	  }
	        	  
	        	  if(coluna.getNome().equals("observacao")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getObservacoesAluno()).orElse(""));
	        	  }
            	  
			});		
    	}	
	}
	
	private void preencherEscolaridadeAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoAluno()).orElse(""));
            	  }
            	  
	        	  if(coluna.getNome().equals("alunoMatriculadoEscolaPublica")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getMatriculadoEscolaPublicaAluno()).orElse(""));
	        	  }
	        	  
            	  if(coluna.getNome().equals("nivelEscolaridade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNivelEscolaridadeAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getGrauInscrucaoAluno())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getGrauInscrucaoAluno());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTipoEscolaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("TurnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTurnoAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getEscolaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSerieEscolaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRegiaoAdministrativaEscolaAluno()).orElse(""));            	  
            	  }
			});		
    	}	
	}
	
	private void preencherDocumentosAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCpfAluno()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getNisAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPispasepAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getIdentidadeAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getOrgaoExpedidoCiAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfCiAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(dadosTO.getDataEmissaoCiAluno())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataEmissaoCiAluno()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getTituloEleitorAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getZonaTituloAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSessaoTituloAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCertificadoReservistaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getRegiaoMilitarReservistaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfReservistaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCnhAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCategoriaCnhAluno()).orElse(""));
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(dadosTO.getDataVencimentoCnhAluno())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(dadosTO.getDataVencimentoCnhAluno()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getCarteiraTrabalhoAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getSerieCarteiraTrabalhoAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getUfCarteiraTrabalhoAluno()).orElse(""));
            	  }
			});		
    	}	
	}
		
	private void preencherOutrasInformacoesAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, ExportarDadosBeneficiarioDTO dadosTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getPossuiDeficienteAluno()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaodeficienciaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoProblemaSaudeAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoMedicamentosControladosAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("condicaoMoradia")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getCondicaoMoradiaAluno())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getCondicaoMoradiaAluno());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoCondicaoMoradia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoCondicaoMoradiaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("jaAtendidoEmOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getAtendidoOrgaoRedeAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoAtendidoOrgaoRedeAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getBeneficiosAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoRedeApSocRelevAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getDescricaoRedeApoioSocialAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  if(Objects.nonNull(dadosTO.getValorBeneficiosAluno())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getValorBeneficiosAluno());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(dadosTO.getOrigemRendaAluno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("origemBeneficio")) {
            		  if(StringUtils.isNotEmpty(dadosTO.getEntidadeSocial())) {
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getEntidadeSocial());
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDataEncaminhamentoAluno());
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(dadosTO.getDescricaoEncaminhamentoAluno());
            		  }else {
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
			});		
    	}	
	}
	
}
