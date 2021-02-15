-------------------------------------------------------------------------------
ALTER TABLE unidades  DROP CONSTRAINT arquivo_unidade;

update unidades
   set id_arquivo_logomarca = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_logomarca );
  
ALTER TABLE unidades
  ADD CONSTRAINT arquivo_unidade FOREIGN KEY (id_arquivo_logomarca)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;


-------------------------------------------------------------------------------
ALTER TABLE instituicoes  DROP CONSTRAINT arquivo_instituicao_fk;

update instituicoes
   set id_arquivo_logomarca = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_logomarca );
  
ALTER TABLE instituicoes
  ADD CONSTRAINT arquivo_instituicao_fk FOREIGN KEY (id_arquivo_logomarca)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;



-------------------------------------------------------------------------------
ALTER TABLE pessoas_fisicas  DROP CONSTRAINT arquivo_pessoa_fisica_fk;

update pessoas_fisicas
   set id_arquivo_foto = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_foto );
  
ALTER TABLE pessoas_fisicas
  ADD CONSTRAINT arquivo_pessoa_fisica_fk FOREIGN KEY (id_arquivo_foto)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;


-------------------------------------------------------------------------------