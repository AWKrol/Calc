package Calculator;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ConnectionDB extends OutputData{

    String url = getProperties("url");
    String user = getProperties("user");
    String password = getProperties("password");

    public ConnectionDB() {
    }

    public ConnectionDB(String fio, double loanTerm, double loanAmount, double interestRate) {
        super(fio, loanTerm, loanAmount, interestRate);
    }

    /**
     * метод выполняет SELECT запрос к БД и возвращает ResultSet
     */
    public ResultSet connectionBDAndSelect(String requestSql) {
        ResultSet resultSet = null;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(requestSql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * метод выполняет INSERT, UPDATE, DELETE запросы к БД
     */
    public void connectionBDAndInsert(String requestSql) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(requestSql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод получает client_id клиента из БД по ФИО
     */
    public String getClientIdFio() {
        String clientId = "null";
        String requestSql = "SELECT id_client FROM client_data WHERE fio_client = '" + fio+ "';";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                clientId = resultSet.getString("id_client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    /**
     * метод получает все значения client_id клиента из БД и сохраняет их в List
     */
    public List<String> getAllClientId() {
        List<String> clientIdList = new ArrayList<>();
        String requestSql = "SELECT id_client FROM client_data;";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                clientIdList.add(resultSet.getString("id_client"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIdList;
    }


    /**
     * метод добавляет в БД клиента, если его там нет
     */
    public void addClientBD() {
        String requestSql; //= "INSERT INTO client_data VALUES('C01','"+fio+"');";
        String idClient = getClientIdFio();
        List<String> clientIdList = getAllClientId();
        String id_client = "C" + randomNum();

        if(idClient.equals("null")) {
            while (clientIdList.contains(id_client)){
                id_client = "C" + randomNum();
            }
            requestSql = "INSERT INTO client_data VALUES('" +id_client+ "','"+fio+"');";
            connectionBDAndInsert(requestSql);
        }
    }

    /**
     * метод получает значение по ключу из application.properties
     */
    public String getProperties(String key) {
        File file = new File("C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\resources\\application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    /**
     * метод удаляет данные из таблицы БД
     */
    public void deleteDataTablDB(String tabl) {
        String requestSql = "DELETE FROM "+tabl+";";
            connectionBDAndInsert(requestSql);
    }

    /**
     * метод генерирует числа для id
     */
    public String randomNum() {
        String NumStr;
        Random random = new Random();
        int x = random.nextInt(99) + 1;
        if((x-9) < 0) {
            NumStr = "0" + x;
        } else {
            NumStr = String.valueOf(x);
        }
        return NumStr;
    }
}
