package no.husbanken.laan.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: e215570
 * Date: 12.05.18
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
public class Laanesoeknad {
    @NotEmpty
    @Valid
    private HashSet<Lanetaker> lanetakere = new HashSet<Lanetaker>();

    @Positive
    @ApiModelProperty(notes = "Lånebeløp", required =true)
    private int lanebelop;
    @NotNull

    @ApiModelProperty(notes = "Behov", required =true)
    private String behov;

    @ApiModelProperty(notes = "Autogenerert søknadsid" , readOnly =true)
    private int soknadsid;

    @ApiModelProperty(notes = "Søknadsstatus" , readOnly =true)
    private String status;

    public int getSoknadsid() {
        return soknadsid;
    }

    public void setSoknadsid(int soknadsid) {
        this.soknadsid = soknadsid;
    }

    public HashSet<Lanetaker> getLanetakere() {
        return lanetakere;
    }

    public void setLanetakere(HashSet<Lanetaker> lanetakere) {
        this.lanetakere = lanetakere;
    }

    public int getLanebelop() {
        return lanebelop;
    }

    public void setLanebelop(int lanebelop) {
        this.lanebelop = lanebelop;
    }

    public String getBehov() {
        return behov;
    }

    public void setBehov(String behov) {
        this.behov = behov;
    }
}
