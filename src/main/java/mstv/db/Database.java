package mstv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

enum Database {

    ADMIN("motorsport_admin", "postgres", "GfhjkmGjEvjkxfyb.123qwe"),
    USER("motorsport_user", "postgres", "GfhjkmGjEvjkxfyb.123qwe"),
    CONTENT("motorsport_content", "motorsport_content_user", "Rwcflva72cD"),
    SUBSCRIPTION("motorsport_subscription", "postgres", "GfhjkmGjEvjkxfyb.123qwe");

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private final String url;
    private final String userName;
    private final String password;

    Database(String dbName, String userName, String password) {
        url = DB_URL + dbName;
        this.userName = userName;
        this.password = password;
    }

    static Connection getConnectionTo(Database database) throws SQLException {
        return DriverManager.getConnection(database.url, database.userName, database.password);
    }
}