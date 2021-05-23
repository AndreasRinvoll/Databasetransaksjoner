package demo.databasetransaksjoner.repositories;

import demo.databasetransaksjoner.Profiles;
import demo.databasetransaksjoner.models.Ordre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.Map;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 23/05/2021 at 14:46
 */

@Slf4j
@Repository
@RequiredArgsConstructor
@Profile({Profiles.TRANSACTIONAL})
public class OrdreTransactionalRepository implements OrdreRepository {

    private final JdbcTemplate jdbc;

    @Transactional
    public boolean createOrdre(final Ordre ordre) {
        final KeyHolder kIdHolder = createKunde(ordre);
        return createVare(ordre, kIdHolder.getKey().intValue());
    }

    public boolean createVare(final Ordre ordre, final int kId) {
        try {
            final String insertOrdreQuery = "INSERT INTO Vare (KId,varenavn,pris) VALUES(?,?,?);";
            return jdbc.update(insertOrdreQuery, kId, ordre.getVarenavn(), ordre.getPris()) == 1;
        } catch (Exception e) {
            log.error("Failed to create new Vare with exception " + e);
            return false;
        }
    }

    public KeyHolder createKunde(final Ordre ordre) {
        final String insertKundeQuery = "INSERT INTO Kunde (fornavn,etternavn,adresse) VALUES(?,?,?);";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbc.update(connection -> {

                final PreparedStatement statement = connection.prepareStatement(insertKundeQuery, new String[]{"KId"});
                statement.setString(1, ordre.getFornavn());
                statement.setString(2, ordre.getEtternavn());
                statement.setString(3, ordre.getAdresse());
                return statement;

            }, keyHolder);

            return keyHolder;

        } catch (Exception e) {
            log.error("Failed to create new Kunde with exception " + e);
            throw new RuntimeException(e);
        }
    }
}
