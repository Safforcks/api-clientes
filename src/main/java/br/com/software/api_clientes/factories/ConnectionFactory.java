package br.com.software.api_clientes.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private String host;
    private String user;
    private String pass;

    public Connection createConnection() throws Exception {

        host = "jdbc:postgresql://localhost:5435/bd-apiclientes";
        user = "software";
        pass = "software";

        return DriverManager.getConnection(host, user, pass);
    }
}
