import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;


@Data
@Accessors(chain = true)
public class Response {
        private String statusText;
        private int statusCode;
        private Map<String,String> headers;
        private String payload;
}
