package br.com.fiap.biooffset.model;

public enum Status {
    EM_ANALISE("em análise"),
    APROVADO("aprovado"),
    REJEITADO("rejeitado");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
