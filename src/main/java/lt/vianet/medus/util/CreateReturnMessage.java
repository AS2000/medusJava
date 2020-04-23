package lt.vianet.medus.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CreateReturnMessage {


    public Map createReturnMessage(String text) {
        Map<String, Boolean> message = new HashMap<>();
        message.put(text, Boolean.TRUE);
        return message;
    }
}
