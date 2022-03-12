//https://www.tutorialspoint.com/sqlite/sqlite_java.htm

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    final static String URL = "jdbc:sqlite:D:/Practice/sqllite/db/test.db";
    private static Connection c = null;

    public static void main(String[] args) {
        createDB();
        insert(1, "Paul", 32, "California", 20000.00f );
        insert(2, "Allen", 25, "Texas", 15000 );
        insert(3, "Teddy", 23, "Norway", 20000.00f );
        insert(4, "Mark", 25, "Rich-Mond", 65000 );
        select();
        update(1);
        select();
        delete(2);
        select();
    }

    private static void getCon() {
        System.out.println("getCon");

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    private static void createDB() {
        System.out.println("create");
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            getCon();
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private static void insert(int p_id, String p_name, int p_age, String p_address, float p_salary) {
        System.out.println("insert");
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            getCon();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (" + p_id + ", '" + p_name + "', " + p_age + ", '" + p_address + "', " + p_salary + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    private static void select() {
        System.out.println("select");
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            getCon();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");

                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    private static void update(int p_id) {
        System.out.println("update");
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            getCon();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=" + p_id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            System.out.println("Operation done successfully");

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    private static void delete(int p_id) {
        System.out.println("delete");
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            getCon();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from COMPANY where ID=" + p_id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            System.out.println("Operation done successfully");

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
