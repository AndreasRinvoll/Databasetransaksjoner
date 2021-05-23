package demo.databasetransaksjoner.repositories;

import demo.databasetransaksjoner.models.Ordre;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 23/05/2021 at 14:25
 */
public interface OrdreRepository {

    boolean createOrdre(Ordre ordre);
}
