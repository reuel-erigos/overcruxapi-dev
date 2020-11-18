package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.cmd.GetResponsavelFamiliarVigenteCmd;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.ExportacaoDadosAlunoTO;
import br.com.crux.to.ResponsaveisAlunoTO;
import br.com.crux.to.exportacao.GrupoDadosExportar;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;


@Component
public class ExportacaoDadosAlunoExcelFileExporter {
	
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private GetResponsavelFamiliarVigenteCmd getResponsavelFamiliarVigenteCmd;
	
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
			grupoAluno.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
				
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
			

			grupoFamiliar.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
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
			
	        
	        for(int i = 0; i < listaCompletaDadosExportar.getListaDadosExportacao().size(); i++) {
	        	AtomicInteger indexDados = new AtomicInteger(0);
	        	Row dataRow = sheet.createRow(i + 1);
	        	
	        	ExportacaoDadosAlunoTO dados = listaCompletaDadosExportar.getListaDadosExportacao().get(i);

	        	AlunoTO alunoTO        = getAlunoCmd.getTOById(dados.getIdAluno());
	        	ResponsaveisAlunoTO rv = getResponsavelFamiliarVigenteCmd.getResponsavelVigente(alunoTO.getId());
	        	
	        	List<GrupoDadosExportar> ga = grupoAluno.stream().filter(grupo -> grupo.isExportar()).collect(Collectors.toList());
	        	List<GrupoDadosExportar> gf = grupoFamiliar.stream().filter(grupo -> grupo.isExportar()).collect(Collectors.toList());
	        	
	        	
	        	//Dados do Aluno
          	  	preencherDadosPessoaisAluno(ga,indexDados, dataRow, alunoTO);
          	    preencherAdmissaoAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherEscolaridadeAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherDocumentosAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherOutrasInformacoesAluno(ga, indexDados, dataRow, alunoTO);
          	    
          	    //Dados do familiar
          	    if(Objects.nonNull(rv)) {
          	    	preencherDadosPessoaisFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherParentescoFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherResponsavelFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherEscolaridadeFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherDocumentosFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherDadosProfissionaisFamiliar(gf,indexDados, dataRow, rv);
          	    	preencherOutrasInformacoesFamiliar(gf,indexDados, dataRow, rv);
          	    }
                
	        }
	        
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

	
	
	private void preencherOutrasInformacoesFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getTipoEscola()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("turnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getTurno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nomeCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCursoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("periodoCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getPeriodoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getSerieEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getRegiaoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEhDeficiente()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getDescricaoDeficiencia()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getProblemaSaude()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getMedicamentosControlados()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getDescricaoPessoaFisicaAtendidoOrgaoRede()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getOutrosBenSoc()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getRedeApoioSocial()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getRedeApSocRelev()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getValorOutrosBenerficiosSoc())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorOutrosBenerficiosSoc());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getOrigemRendaFamiliar()).orElse(""));
            	  }

			});		
    	}	

	}

	private void preencherDadosProfissionaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_profissionais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("profissao")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getProfissao())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getProfissao());           	  
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("empresa")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getNomeEmpresaTrabalho())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNomeEmpresaTrabalho());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("possuiBolsaFamilia")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getBeneficiarioBolsaFamilia())) {
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getBeneficiarioBolsaFamilia());
            	  }
            	  
            	  if(coluna.getNome().equals("valorBolsaFamilia")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getValorBolsaFamilia())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorBolsaFamilia());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("valorRenda")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getValorRenda())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorRenda());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("telefoneComercial")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getTelefoneComercial())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTelefoneComercial());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("situacaoTrabalho")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getSituacaoTrabalho())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSituacaoTrabalho());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("motivoNaoTrabalhar")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica()) && Objects.nonNull(rv.getFamiliar().getPessoasFisica().getMotivoNaoTrab())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getMotivoNaoTrab());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
			});		
    	}	

	}

	private void preencherDocumentosFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
           			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCpf()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNis()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNumeroPisPasep()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getIdentidade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getOrgaoCi()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getUfCi()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getDataEmissaoCI())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getDataEmissaoCI().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getTituloEleitor()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getZonaTitulo()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getSessaoTitulo()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNumeroReservista()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getRegiaoMilitarReservista()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getUfRegiaoMilitar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNumeroCNH()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCategoriaCNH()).orElse(""));
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getVencimentoCNH())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getVencimentoCNH().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCts()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getSerieCtps()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getUfCTS()).orElse(""));
            	  }
			});		
    	}	
		
	}

	private void preencherEscolaridadeFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEscolaridade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nivel")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNivelEscolaridade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getGrausInstrucao())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getGrausInstrucao().getDescricao());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
			});		
    	}
	}

	private void preencherResponsavelFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("responsavel")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataVinculacao")) {
                	  if(Objects.nonNull(rv.getDataVinculacao())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getDataVinculacao().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesvinculacao")) {
                	  if(Objects.nonNull(rv.getDataDesvinculacao())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getDataDesvinculacao().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("beneficiarioMesmoEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getMesmoEnderResponsavel()).orElse(""));
                  }

                  if(coluna.getNome().equals("autorizaLevarBuscar")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getTransportaAluno()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("temTutela")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getTutelaAluno()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("responsavelFinanceiro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getResponsavelFinanceiroPeloAluno()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getDescGrauParentesco()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getDescDesligamento()).orElse(""));
                  }
			});		
    	}
	}

	private void preencherParentescoFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("parentesco")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataCadastro")) {
                	  if(Objects.nonNull(rv.getFamiliar().getDataCadastro())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getDataCadastro().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesligamento")) {
                	  if(Objects.nonNull(rv.getFamiliar().getDataDesligamento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getDataDesligamento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("situacaoParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getSituacaoParentesco()).orElse(""));
                  }

                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getDescGrauParentesco()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getDescDesligamento()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("outrasInformacoes")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getDescOutrasInformacoes()).orElse(""));
                  }
			});		
    	}

	}

	private void preencherDadosPessoaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNome()).orElse(""));
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getDataNascimento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getDataNascimento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCidadeNaturalidade()).orElse(""));
                  }

                  if(coluna.getNome().equals("ufNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getUfNascimento()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getSexo()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getRaca()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNomeMae()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getNomePai()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEstadoCivil()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getTipoSangue()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cep")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCep()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEndereco()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCidade()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getBairro()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getUf()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getPontoReferencia()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getTelefoneResidencial()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getCelular()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getEmail()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(rv.getFamiliar().getPessoasFisica().getAutorizaEmail()).orElse(""));
                  }
			});		
    	}
	}

	
	
	private void preencherDadosPessoaisAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNome()).orElse(""));
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(Objects.nonNull(alunoTO.getPessoaFisica().getDataNascimento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getDataNascimento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCidadeNaturalidade()).orElse(""));
                  }

                  if(coluna.getNome().equals("uf")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getUfNascimento()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getSexo()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getRaca()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNomeMae()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNomePai()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEstadoCivil()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getTipoSangue()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cep")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCep()).orElse(null));
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEndereco()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCidade()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getBairro()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getUf()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getPontoReferencia()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getTelefoneResidencial()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCelular()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEmail()).orElse(""));
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getAutorizaEmail()).orElse(""));
                  }
			});		
    	}	
	}
	
	private void preencherAdmissaoAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("admissao")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
	        	  if(coluna.getNome().equals("matricula")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getMatriculaAluno()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("unidade")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getUnidade().getNomeUnidade()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("nivelTurma")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getNivelTurma().getDescricao()).orElse(""));
	        	  }
            	  
	        	  if(coluna.getNome().equals("dataEntrada")) {
	        		  if(Objects.nonNull(alunoTO.getDataEntrada())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getDataEntrada().toLocalDate()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("dataDesligamento")) {
	        		  if(Objects.nonNull(alunoTO.getDataDesligamento())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getDataDesligamento().toLocalDate()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("moraComOsPais")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getMoraPais()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("paisSaoCasados")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPaisCasados()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("alunoMatriculadoEscolaPublica")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getMatriculadoEscPub()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("alunoPublicoPrioritario")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPublicoPrioritario()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("descPessoaBuscaAlunoEscola")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getDescBuscaEscola()).orElse(""));
	        	  }
	        	  
	        	  if(coluna.getNome().equals("desligamento")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getDescDesligamento()).orElse(""));            	  
	        	  }
	        	  
	        	  if(coluna.getNome().equals("observacao")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getObservacoes()).orElse(""));
	        	  }
            	  
			});		
    	}	
	}
	
	private void preencherEscolaridadeAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEscolaridade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nivelEscolaridade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNivelEscolaridade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getGrausInstrucao())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getGrausInstrucao().getDescricao());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getTipoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("TurnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getTurno()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("nomeCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCursoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("periodoCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getPeriodoEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getSerieEscola()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getRegiaoEscola()).orElse(""));            	  
            	  }
			});		
    	}	
	}
	
	private void preencherDocumentosAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCpf()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNis()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNumeroPisPasep()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getIdentidade()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getOrgaoCi()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getUfCi()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getDataEmissaoCI())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getDataEmissaoCI().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getTituloEleitor()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getZonaTitulo()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getSessaoTitulo()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNumeroReservista()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getRegiaoMilitarReservista()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getUfRegiaoMilitar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getNumeroCNH()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCategoriaCNH()).orElse(""));
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getVencimentoCNH())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getVencimentoCNH().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCts()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getSerieCtps()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getUfCTS()).orElse(""));
            	  }
			});		
    	}	
	}
		
	private void preencherOutrasInformacoesAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getEhDeficiente()).orElse(""));           	  
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getDescricaoDeficiencia()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getProblemaSaude()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getMedicamentosControlados()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("condicaoMoradia")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getCondicoesMoradia())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCondicoesMoradia().getDescricao());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoCondicaoMoradia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getCondicaoMoradia()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("jaAtendidoEmOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getAtendidoOrgaoRede()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getDescricaoPessoaFisicaAtendidoOrgaoRede()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getOutrosBenSoc()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getRedeApoioSocial()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getRedeApSocRelev()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica()) && Objects.nonNull(alunoTO.getPessoaFisica().getValorOutrosBenerficiosSoc())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getValorOutrosBenerficiosSoc());
            		  } else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(alunoTO.getPessoaFisica().getOrigemRendaFamiliar()).orElse(""));
            	  }
            	  
            	  if(coluna.getNome().equals("origemBeneficio")) {
            		  if(Objects.nonNull(alunoTO.getEncaminhamentos()) && !alunoTO.getEncaminhamentos().isEmpty()) {
            			  alunoTO.getEncaminhamentos().sort(Comparator.comparing(EncaminhaAlunosTO::getDataEncaminhaAluno).reversed());
            			  EncaminhaAlunosTO encaminhamento = alunoTO.getEncaminhamentos().get(0);

        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(encaminhamento.getEntidadeSocial().getEmpresa().getNomeFantasia());
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(encaminhamento.getDataEncaminhaAluno().toLocalDate()));
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(encaminhamento.getDescricao());
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
