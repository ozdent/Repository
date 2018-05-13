package no.husbanken.laan.model;

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
    private int lanebelop;
    @NotNull
    private String behov;
    private int soknadsid;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
