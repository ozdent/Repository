package no.husbanken.laan.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Lanetaker {

    @Size(min = 11, max = 11)
    @NotNull
    @ApiModelProperty(notes = "fnr", required =true)
    private String fnr;

    @NotNull
    @ApiModelProperty(notes = "navn", required =true)
    private String navn;

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
