import java.util.HashMap;
import java.util.Map;

public class Main {
    static public void main(String[] words) throws Exception {
        Client client = new Client();
        String url = "192.168.0.1";
        Map<String,String> headers = new HashMap<>();
        headers.put("Host","google.com");

        System.out.println(client.get(url,headers));
    }
}
