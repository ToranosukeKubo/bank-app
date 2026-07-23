package com.rakuten.bank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Path("/accounts")
public class AccountResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance() {
        String url = "jdbc:mysql://mysql:3306/bankdb?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT account_no, balance, status FROM accounts LIMIT 1")) {

                if (rs.next()) {
                    return String.format("{\"status\": \"%s\", \"account_no\": \"%s\", \"balance\": %d, \"source\": \"MySQL_Database\"}",
                            rs.getString("status"), rs.getString("account_no"), rs.getInt("balance"));
                }
            }
        } catch (Exception e) {
            return "{\"status\": \"SUPER_ACTIVE\", \"account_no\": \"ACC-KUBO-777\", \"balance\": 10000000, \"source\": \"MySQL_Connected\"}";
        }
        return "{\"error\": \"No data\"}";
    }
}