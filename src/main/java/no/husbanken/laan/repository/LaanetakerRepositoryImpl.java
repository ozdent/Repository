package no.husbanken.laan.repository;

import no.husbanken.laan.model.Laanesoeknad;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: e215570
 * Date: 12.05.18
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class LaanetakerRepositoryImpl implements LaanetakerRepository{

    @Override
    public String finnSoeknad(int soknadsId, Map<Integer, Laanesoeknad> soknadsMap) {
        if(soknadsMap.get(soknadsId) != null)    {
            return "Mottatt";
        } else {
            return "Ukjent";
        }
    }
}
