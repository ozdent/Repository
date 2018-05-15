package no.husbanken.laan.model;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;

public class Laanesoeknad {

    @NotEmpty
    @Valid
    private HashSet<Lanetaker> lanetakere = new HashSet<Lanetaker>();

    @Positive
    @ApiModelProperty(notes = "lanebelop", required =true)
    private int lanebelop;

    @NotNull
    @ApiModelProperty(notes = "behov", required =true)
    private String behov;

    @ApiModelProperty(notes = "Autogenerert soknadsid" , readOnly =true)
    private int soknadsid;

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
