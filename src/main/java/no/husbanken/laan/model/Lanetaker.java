package no.husbanken.laan.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: e215570
 * Date: 12.05.18
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
public class Lanetaker {
    @Size(min = 11, max = 11)
    @NotNull
    private String fnr;
    @NotNull
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
