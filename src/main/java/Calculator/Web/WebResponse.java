package Calculator.Web;

import Calculator.ConnectionDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WebResponse extends ConnectionDB {

    static WebResponse webResponse = new WebResponse();

    public static JSONObject getUsers() {
        return webResponse.getAllTablClientData();
    }

    /**
     * написать метод getUserData(String usersId), возвращает JSON.
     *  {
     *      id : sss
     *      name : sss
     *      mortgageData : [
     *      {id_mort :dddd
     *       data_mort : dddd},
     *       {id_mort :dddd
     *      data_mort : dddd}
     *      ]
     *  }
     */
    public static JSONObject getUserData(String userId) {
        JSONObject jsonObject = null;
        String sqlResponseOne = "SELECT * FROM client_data WHERE id_client = '" + userId + "'";
        String sqlResponseTwo = "SELECT id_mortgage, data_mortgage " +
                "FROM mortgage_data WHERE id_client = '" + userId + "'";
        ResultSet resultSetOne = webResponse.connectionBDAndSelect(sqlResponseOne);
        try {
            if (!resultSetOne.isBeforeFirst()) {
                return jsonObject;
            }
            }catch(SQLException e){
            e.printStackTrace();
        }
        ResultSet resultSetTwo = webResponse.connectionBDAndSelect(sqlResponseTwo);
        JSONObject jsonObjectOne = ConnectionDB.converterJSONObject(resultSetOne);
        JSONArray jsonArrayTwo = ConnectionDB.converterJSONArray(resultSetTwo);
        jsonObjectOne.put("dataMortgage", jsonArrayTwo);
        return jsonObjectOne;
    }

}
