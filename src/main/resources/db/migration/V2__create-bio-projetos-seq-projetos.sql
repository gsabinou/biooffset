-- Constraint PK para a FK funcionar
ALTER TABLE BIO_EMPRESAS
    ADD CONSTRAINT pk_empresas PRIMARY KEY (ID_EMPRESA);

CREATE SEQUENCE SEQ_PROJETOS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
  NOCYCLE;

CREATE TABLE BIO_PROJETOS_AMBIENTAIS
(
    ID_PROJETO                INTEGER DEFAULT SEQ_PROJETOS.nextval NOT NULL,
    EMPRESA_DESENVOLVEDORA_ID INTEGER                              NOT NULL,
    DS_NOME                   VARCHAR2(100) NOT NULL,
    DS_PROJETO                VARCHAR2(100) NOT NULL,
    NM_BIOMA                  VARCHAR2(30) NOT NULL,
    NR_LATITUDE               NUMBER(10, 8) NOT NULL,
    NR_LONGITUDE              NUMBER(11, 8) NOT NULL,
    ST_STATUS                 VARCHAR2(20) NOT NULL,

    -- Restrição de Chave Estrangeira (Relacionamento com a tabela EMPRESAS)
    CONSTRAINT fk_proj_amb_empresa
        FOREIGN KEY (empresa_desenvolvedora_id)
            REFERENCES BIO_EMPRESAS (ID_EMPRESA)
            ON DELETE CASCADE
);