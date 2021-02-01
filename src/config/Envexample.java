package config;
/**
 * Byt namnet på denna klass till Env och byt ut dessa variabler till de som passar din databas
 * Mvh Pierre
 */
public class Envexample {
    public static String dbName = "soloadventure";
    public static String driverName ="com.mysql.jdbc.Driver";
    public static String conURL = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=latin1";
    public static String user = "";
    public static String pass = "";
    public static String GameMessageBoxTitle = "Game GUI";
    public static String EditorMessageBoxTitle = "Game Editor GUI";
    public static String LoginMessageBoxTitle = "Login GUI";
    public static String RegisterMessageBoxTitle = "Register GUI";
    public static String DBMessageBoxTitle = "DBManager GUI";
    public static String SecurityMessageBoxTitle = "Security GUI";

}
