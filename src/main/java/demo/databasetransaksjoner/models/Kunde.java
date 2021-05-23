package demo.databasetransaksjoner.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Kunde {
    private int KId;
    private String Fornavn;
    private String Etternavn;
    private String Adresse;
}
