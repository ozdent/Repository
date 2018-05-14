package no.husbanken.laan.repository;

import no.husbanken.laan.model.Laanesoeknad;

import java.util.Map;

public interface LaanetakerRepository {
    String hentStatus(int soknadsId);
    void putLaaneSoknad(Laanesoeknad laanesoeknad);
    Laanesoeknad hentLaaneSoknad(int soknadsId);
}
