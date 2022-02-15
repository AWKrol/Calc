package Calculator.Web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GetController {

    @GetMapping("/users")
    @ResponseBody
    JSONObject users() {
        return WebResponse.getUsers();
    }

    @GetMapping(value = "/user/{userId}", produces = "application/json")
    @ResponseBody
    ResponseEntity userData(@PathVariable String userId) {
    JSONObject jsonObject = WebResponse.getUserData(userId);
    if(jsonObject != null) {
        return ResponseEntity.ok(jsonObject);
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
