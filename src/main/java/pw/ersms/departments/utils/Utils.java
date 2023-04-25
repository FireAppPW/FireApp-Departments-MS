package pw.ersms.departments.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Object response(Object result, String description) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        response.put("message", description);
        response.put("success", true);
        return response;
    }
}
