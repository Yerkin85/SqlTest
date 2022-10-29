package com.company;
import java.sql.*;
import java.lang.Exception;
import java.util.Scanner;

public class Main {
    //private static Connection c = null;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
             try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "123456");
            System.out.println("Database successfully connected");
            /* String sql = "create table users" + "(name text , lat1 real, lon1 real, lat2 real, lon2 real, distance real)";
            statement = c.createStatement();
            statement.executeUpdate(sql);
            System.out.println("table created");
            statement.close();*/
            c.close();
        } catch (Exception e) {
            System.out.println("Something goes wrong");
            e.printStackTrace();
        }
         insertRecord();
        // selectData();
        }
        public static void insertRecord() throws SQLException {
            boolean finish = false;
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "123456");
        String sql = "insert into users(name, lat1, lon1, lat2, lon2, distance) values(?,?,?,?,?,?)";
          while(!finish) {
              try{
                PreparedStatement preparedStatement = c.prepareStatement(sql);
                System.out.println("Введите имя : ");
                preparedStatement.setString(1, sc.nextLine());

                System.out.println("Введите широту точки №1: ");
                double lat1 = sc.nextDouble();
                preparedStatement.setDouble(2, lat1);

                System.out.println("Введите долготу точки №1: ");
                double lon1 = sc.nextDouble();
                preparedStatement.setDouble(3, lon1);

                System.out.println("Введите широту точки №2: ");
                double lat2 = sc.nextDouble();
                preparedStatement.setDouble(4, lat2);

                System.out.println("Введите долготу точки №2: ");
                double lon2 = sc.nextDouble();
                preparedStatement.setDouble(5, lon2);

                double distance = Math.round((2 * Math.asin(Math.sqrt(Math.pow(Math.sin((Math.toRadians(lat2) - Math.toRadians(lat1)) / 2), 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.pow(Math.sin((Math.toRadians(lon2) - Math.toRadians(lon1)) / 2), 2)))) * 6371);
                preparedStatement.setDouble(6, distance);
                int rows = preparedStatement.executeUpdate();
                finish = true;}
              catch (Exception e) {
                  String entered = sc.nextLine();

                  System.out.println("Координаты точек были введены неправильно.");
                  System.out.println("Укажите координаты точек в следующей форме: 00,0000(Пример:74,1456).");
                  e.printStackTrace();
              }
              }
            System.out.println("inserted successfully");
            }

    /* public static void selectData() throws SQLException {
              Statement statement;
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "123456");
              statement = c.createStatement();
              ResultSet res = statement.executeQuery("select * from users;");
              while(res.next()) {
                  String name = res.getString("name");
                  double lat1 = res.getDouble("lat1");
                  double lon1 = res.getDouble("lon1");
                  double lat2 = res.getDouble("lat2");
                  double lon2 = res.getDouble("lon2");
                  double distance = res.getDouble("distance");
                // System.out.println(String.format("name = %s lat1 = %s lon1 = %s lat2 = %s lon2 = %s distance = %s", name,lat1,lon1,lat2,lon2,distance));
                  System.out.println("Имя: " + name + "\n" + "Широта точки №1: " + lat1 + "\n" + "Долгота точки №1: " + lon1 + "\n" + "Широта точки №2: " + lat2 +"\n" + "Долгота точки №2: " + lon2 + "\n" + "Расстояние между координатами: " + distance + " км");
              }
              res.close();
              statement.close();

            }*/
}
