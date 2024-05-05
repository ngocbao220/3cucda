package OurPackage.Module;

public class Dictionary {
    public static String dbPath;

    private static String dbName;

    public static String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        Dictionary.dbPath = dbPath;
    }

    public static String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        Dictionary.dbName = dbName;
    }

    public Dictionary(String dbPath, String dbName) {
        Dictionary.dbPath = dbPath;
        Dictionary.dbName = dbName;
    }

}
