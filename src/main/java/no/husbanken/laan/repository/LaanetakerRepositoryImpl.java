package no.husbanken.laan.repository;

import no.husbanken.laan.model.Laanesoeknad;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class LaanetakerRepositoryImpl implements LaanetakerRepository{

    public  Map<Integer, Laanesoeknad> soknadsMap = new HashMap<Integer, Laanesoeknad>();
    private int soknadsid = 8000;
    private final String  MESSAGE_MOTTAT = "Mottatt";
    private final String  MESSAGE_UKJENT = "Ukjent";

    @Override
    public String hentStatus(int soknadsId) {
        Laanesoeknad laanesoeknad =  hentLaaneSoknad(soknadsId);
        if(laanesoeknad != null)    {
            return MESSAGE_MOTTAT;
        }
        return MESSAGE_UKJENT;
    }

    @Override
    public void putLaaneSoknad(Laanesoeknad laanesoeknad) {
        laanesoeknad.setSoknadsid(++soknadsid);
        soknadsMap.put(soknadsid,laanesoeknad) ;
    }

    @Override
    public Laanesoeknad hentLaaneSoknad(int soknadsId) {
        return soknadsMap.get(soknadsId);
    }
}