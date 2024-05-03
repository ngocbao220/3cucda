package OurPackage.Module;

public class Dictionary {
    private String dbPath;

    private String dbName;

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Dictionary(String dbPath, String dbName) {
        this.dbPath = dbPath;
        this.dbName = dbName;
    }

}
