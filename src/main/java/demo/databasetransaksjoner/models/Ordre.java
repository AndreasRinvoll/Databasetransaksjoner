package demo.databasetransaksjoner.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ordre {
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String varenavn;
    private double pris;
}
