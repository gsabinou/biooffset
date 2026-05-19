package br.com.fiap.biooffset.model;

public enum Biomas {
    AMAZONIA("amazonia"),
    CERRADO("cerrado"),
    CAATINGA("caatinga"),
    PAMPAS("pampas"),
    PANTANAL("pantanal");

    private String bioma;

    Biomas(String bioma) {
        this.bioma = bioma;
    }

    public String getBioma() {
        return this.bioma;
    }

}
