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
     * метод получает client_id клиента из БД по ФИО,
     * fio берется из переменной экземпляра класса
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
     * метод получает client_id клиента из БД по ФИО,
     * fio берется из параметра метода
     */
    public String getClientIdFio(String fioClient) {
        String clientId = "null";
        String requestSql = "SELECT id_client FROM client_data WHERE fio_client = '" + fioClient+ "';";
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
     * метод получает id_mortgage клиента из БД по data_mortgage
     */
    public String getMortgageIdByDataMortgage() {
        String mortgageId = "null";
        String requestSql = "SELECT id_mortgage FROM mortgage_data WHERE data_mortgage = '" + fileMortgageData+ "';";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                mortgageId = resultSet.getString("id_mortgage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mortgageId;
    }

    /**
     * метод получает id_mortgage клиента из БД по data_mortgage = fileName
     */
    public String getMortgageIdByDataMortgage(String fileName) {
        String mortgageId = "null";
        String requestSql = "SELECT id_mortgage FROM mortgage_data WHERE data_mortgage = '" + fileName+ "';";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                mortgageId = resultSet.getString("id_mortgage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mortgageId;
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
     * метод получает все значения id_mortgage из таблицы mortgage_data БД и сохраняет их в List
     */
    public List<String> getAllMortgageIdMort() {
        List<String> clientIdList = new ArrayList<>();
        String requestSql = "SELECT id_mortgage FROM mortgage_data;";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                clientIdList.add(resultSet.getString("id_mortgage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIdList;
    }

    /**
     * метод получает все значения id_mortgage из таблицы repayment_data БД и сохраняет их в List
     */
    public List<String> getAllMortgageIdRepay() {
        List<String> clientIdList = new ArrayList<>();
        String requestSql = "SELECT id_mortgage FROM repayment_data;";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                clientIdList.add(resultSet.getString("id_mortgage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIdList;
    }

    /**
     * метод получает все значения id_repay из таблицы repayment_data БД и сохраняет их в List
     */
    public List<String> getAllIdRepay() {
        List<String> clientIdList = new ArrayList<>();
        String requestSql = "SELECT id_repay FROM repayment_data;";
        ResultSet resultSet = connectionBDAndSelect(requestSql);
        try {
            while (resultSet.next()){
                clientIdList.add(resultSet.getString("id_repay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIdList;
    }

    /**
     * метод добавляет в БД(таблица client_data) клиента, если его там нет
     * fio принимается из экземпляра класса
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
     * метод добавляет в БД клиента(таблица client_data), если его там нет
     * @param fioClient принимается в качестве входного параметра
     */
    public void addClientBDFIO(String fioClient) {
        String requestSql; //= "INSERT INTO client_data VALUES('C01','"+fio+"');";
        String idClient = getClientIdFio(fioClient);
        List<String> clientIdList = getAllClientId();
        String id_client = "C" + randomNum();

        if(idClient.equals("null")) {
            while (clientIdList.contains(id_client)){
                id_client = "C" + randomNum();
            }
            requestSql = "INSERT INTO client_data VALUES('" +id_client+ "','"+fioClient+"');";
            connectionBDAndInsert(requestSql);
        }
    }

    /**
     * метод добавляет данные в таблицу mortgage_data БД
     */
    public void addDataMortgageBD() { // переписать на  do while
        String id_mortgage;
        String requestSql;
        String idClient = getClientIdFio();
        //String idMortgage = getMortgageIdByClientId(idClient);
        List<String> clientMortList = getAllMortgageIdMort();
        do {
            id_mortgage = "M" + randomNum();
        }
        while (clientMortList.contains(id_mortgage));
        requestSql = "INSERT INTO mortgage_data VALUES('"+id_mortgage+"','"+idClient+"','"+fileMortgageData+"');";
        connectionBDAndInsert(requestSql);

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

    /**
     * метод заносит ФИО заемщиков в БД(client_data) из файлов директории mortgageData,
     * если их там нет
     */
    public void addFioDBClientData() {
        String fioClient = "";
        File folder = new File(getProperties("directory.path"));
        File [] fileList = folder.listFiles();
        if(fileList.length > 0) {
            for (File file : folder.listFiles()) {
                fioClient = getFIOFromFile(file.getName());
                addClientBDFIO(fioClient);
            }
        }
    }

    /**
     * метод добавляет данные в таблицу repayment_data БД
     */
    public void addDataRepaymentBD() {  // Разобраться с запросами в БД
        //String id_mortgage;
        String id_repay;
        String requestSql;
        //String idClient = getClientIdFio();
        String idMortgage = getMortgageIdByDataMortgage();
        List<String> idMortList = getAllMortgageIdRepay();
        List<String> idRepayList = getAllIdRepay();
        if(!idMortList.contains(idMortgage)) {
            do {
                id_repay = "R" + randomNum();
            }
            while (idRepayList.contains(id_repay));
            requestSql = "INSERT INTO repayment_data VALUES(" +
                    "'"+id_repay+"','"+idMortgage+"','"+fileRepaymentData+"');";
            connectionBDAndInsert(requestSql);
        } else
            System.out.println("ОШИБКА: в БД уже есть такая запись!!!");
    }

    /**
     * метод добавляет данные в таблицу repayment_data БД,
     * данные берем из fileName
     */
    public void addDataRepaymentBD(String fileName) {
        //String id_mortgage;
        String id_repay;
        String requestSql;
        //String idClient = getClientIdFio();
        String idMortgage = getMortgageIdByDataMortgage(fileName);
        List<String> idMortList = getAllMortgageIdRepay();
        List<String> idRepayList = getAllIdRepay();
        if(!idMortList.contains(idMortgage)) {
            do {
                id_repay = "R" + randomNum();
            }
            while (idRepayList.contains(id_repay));
            requestSql = "INSERT INTO repayment_data VALUES(" +
                    "'"+id_repay+"','"+idMortgage+"','"+fileName+"');";
            connectionBDAndInsert(requestSql);
        } else
            System.out.println("ОШИБКА: в БД уже есть такая запись!!!");
    }

}
