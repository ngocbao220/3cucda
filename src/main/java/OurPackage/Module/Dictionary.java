package OurPackage.Module;

public class Dictionary {
    public static String dbPath;

    private static String dbName;

    public static String getDbPath() {
        return dbPath;
    }

    public static String getDbName() {
        return dbName;
    }

    public Dictionary(String dbPath, String dbName) {
        this.dbPath = dbPath;
        this.dbName = dbName;
    }

}
