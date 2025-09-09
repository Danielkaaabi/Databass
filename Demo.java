package Demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/webbshop";
        String user = "root";
        String password = "Tepigee1!";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT DISTINCT k.förnamn, k.efternamn " +
                             "FROM Kund k " +
                             "JOIN Beställning b ON k.kund_id = b.kund_id " +
                             "JOIN Beställningsrad br ON b.beställning_id = br.beställning_id " +
                             "JOIN Produkt p ON br.produkt_id = p.produkt_id " +
                             "JOIN ProduktKategori pk ON p.produkt_id = pk.produkt_id " +
                             "JOIN Kategori ka ON pk.kategori_id = ka.kategori_id " +
                             "WHERE p.färg = 'svart' AND p.storlek = '38' AND p.märke = 'SweetPants' AND ka.namn = 'Byxor'"
             )) {

            System.out.println("Kunder som har köpt svarta byxor i storlek 38 av märket SweetPants:");
            System.out.println("====================================================================");

            while (rs.next()) {
                String förnamn = rs.getString("förnamn");
                String efternamn = rs.getString("efternamn");
                System.out.println(förnamn + " " + efternamn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
