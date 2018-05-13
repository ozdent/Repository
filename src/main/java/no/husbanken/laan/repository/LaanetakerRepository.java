package no.husbanken.laan.repository;

import no.husbanken.laan.model.Laanesoeknad;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: e215570
 * Date: 12.05.18
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public interface LaanetakerRepository {
    String finnSoeknad(int soknadsId, Map<Integer, Laanesoeknad> soknadsMap);
}
