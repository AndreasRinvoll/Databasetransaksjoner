package demo.databasetransaksjoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
public class OrdreController {

    @Autowired
    private JdbcTemplate db;

    /*@PostMapping("/lagre")
    @Transactional
    public void lagreMelding(Ordre o) {
        String sql1 = "INSERT INTO Kunde (fornavn,etternavn,adresse) VALUES(?,?,?);";
        String sql2 = "INSERT INTO Ordre (KId,varenavn,pris) VALUES(?,?,?);";

        KeyHolder id = new GeneratedKeyHolder();
        try {
            db.update(con -> {
                PreparedStatement par = con.prepareStatement(sql1, new String[]{"KId"});
                par.setString(1, o.getFornavn());
                par.setString(2, o.getEtternavn());
                par.setString(3, o.getAdresse());
                return par;
            }, id);
            int kid = id.getKey().intValue();
            db.update(sql2,kid,o.getVarenavn(),o.getPris());
        }catch (Exception e){

        }
    }*/

    @PostMapping("/lagre")
    public void altLagreMelding(Ordre ordre){
        if(lagreKundeOK(ordre)){
            String sql1 = "SELECT * FROM Kunde ORDER BY KId DESC LIMIT 1;";
            List<Kunde> kunde = db.query(sql1, new BeanPropertyRowMapper<>(Kunde.class));
            int KId = kunde.get(0).getKId();
            System.out.println("Kundenummeret er : " + KId);

            if(!lagreVareOk(KId, ordre)){
                String sql3 = "DELETE FROM Kunde WHERE KId = ?;";
                db.update(sql3, KId);
                System.out.println("Vare kunne ikke oppdateres og Kunde " + KId + " er slettet.");
            }
        }
    }

    private boolean lagreKundeOK(Ordre ordre){
        try{
            String sql = "INSERT INTO Kunde (fornavn,etternavn,adresse) VALUES(?,?,?);";
            db.update(sql, ordre.getFornavn(), ordre.getEtternavn(), ordre.getAdresse());
            System.out.println("Kunde er registrert!");
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private boolean lagreVareOk(int KId, Ordre ordre){
        System.out.println(KId + " " + ordre.getVarenavn() + " " + ordre.getPris());
        try {
            String sql2 = "INSERT INTO Vare (KId,Varenavn,Pris) VALUES(?,?,?);";
            db.update(sql2, KId, ordre.getVarenavn(), ordre.getPris());
            System.out.println("Vare er oppdatert!");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
