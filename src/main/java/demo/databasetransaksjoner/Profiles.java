package demo.databasetransaksjoner;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 23/05/2021 at 14:17
 */

public final class Profiles {

    private Profiles() {
        throw new UnsupportedOperationException("This is a utility class and is not mean to be instantiated!");
    }

    public static final String TRANSACTIONAL = "transactional";
    public static final String NON_TRANSACTIONAL = "non_Transactional";
}
