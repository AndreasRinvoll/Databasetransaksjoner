package demo.databasetransaksjoner.repositories;

import demo.databasetransaksjoner.Profiles;
import demo.databasetransaksjoner.models.Kunde;
import demo.databasetransaksjoner.models.Ordre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 23/05/2021 at 14:07
 */

@Slf4j
@Repository
@RequiredArgsConstructor
@Profile({Profiles.NON_TRANSACTIONAL})
public class OrdreNonTransactionalRepository implements OrdreRepository {

    private final JdbcTemplate jdbc;

    public boolean createOrdre(final Ordre ordre) {
        if (createKunde(ordre)) {
            final String selectAllKundeQuery = "SELECT * FROM Kunde ORDER BY KId DESC LIMIT 1;";
            final List<Kunde> kunder = jdbc.query(selectAllKundeQuery, new BeanPropertyRowMapper<>(Kunde.class));
            final int kundeId = kunder.stream().findFirst().map(Kunde::getKId).orElseThrow(RuntimeException::new);

            return createVare(kundeId, ordre);
        }
        return false;
    }

    private boolean createKunde(final Ordre ordre) {
        try {
            final String insertKundeQuery = "INSERT INTO Kunde (fornavn,etternavn,adresse) VALUES(?,?,?);";
            return jdbc.update(insertKundeQuery, ordre.getFornavn(), ordre.getEtternavn(), ordre.getAdresse()) == 1;
        } catch (Exception e) {
            log.error("Inserting kunde failed with exception " + e);
            return false;
        }
    }

    private boolean createVare(final int KId, final Ordre ordre) {
        try {
            final String insertVareQuery = "INSERT INTO Vare (KId,Varenavn,Pris) VALUES(?,?,?);";
            return jdbc.update(insertVareQuery, KId, ordre.getVarenavn(), ordre.getPris()) == 1;
        } catch (Exception e) {
            log.error("Inserting vare failed with exception " + e);
            return false;
        }
    }
}
