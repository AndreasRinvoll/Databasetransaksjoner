package demo.databasetransaksjoner;

public class Kunde {
    private int KId;
    private String Fornavn;
    private String Etternavn;
    private String Adresse;

    public Kunde(int KId, String fornavn, String etternavn, String adresse) {
        this.KId = KId;
        Fornavn = fornavn;
        Etternavn = etternavn;
        Adresse = adresse;
    }

    public Kunde() {
    }

    public int getKId() {
        return KId;
    }

    public void setKId(int KId) {
        this.KId = KId;
    }

    public String getFornavn() {
        return Fornavn;
    }

    public void setFornavn(String fornavn) {
        Fornavn = fornavn;
    }

    public String getEtternavn() {
        return Etternavn;
    }

    public void setEtternavn(String etternavn) {
        Etternavn = etternavn;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
