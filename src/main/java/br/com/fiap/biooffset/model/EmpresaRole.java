package br.com.fiap.biooffset.model;

public enum EmpresaRole {
    DESENVOLVEDOR("desenvolvedor"),
    COMPRADOR("comprador");

    private String role;

    EmpresaRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
