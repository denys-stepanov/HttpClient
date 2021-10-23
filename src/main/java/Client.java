import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Client implements HttpClient {
    Response response = new Response();
    StringBuilder builder = new StringBuilder();

    @Override
    public Response get(String url, Map<String, String> headers) throws Exception {
        Socket socket = new Socket(url,80);

        builder.append("GET")
                .append(" ")
                .append(" HTTP/1.0\r\n");


        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.append(header.getKey())
                    .append(" ")
                    .append(header.getValue())
                    .append("\r\n");
        }
        builder.append("\r\n");

        socket.getOutputStream().write(
                builder.toString().getBytes(StandardCharsets.UTF_8)
        );
        socket.getOutputStream().flush();


        Reader reader  = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);


        String inputLine = null;
        Map<String,String> headersMap = new HashMap<>();
        StringBuffer responseString = new StringBuffer();


        response.setStatusText(bufferedReader.readLine());
        while(true) {
            inputLine = bufferedReader.readLine();
            System.out.println(inputLine);
            if(inputLine.isEmpty()) break;
            String[] headerParts = inputLine.split(": ");
            headersMap.put(headerParts[0],headerParts[1]);
        }

        response.setHeaders(headersMap);

        while ((inputLine = bufferedReader.readLine())!=null ){
            responseString.append(inputLine);
            responseString.append("\n");
        }


        bufferedReader.close();
        response.setPayload(responseString.toString());
        socket.close();
        return response;
    }

    @Override
    public Response post(String url, Map<String, String> headers, byte[] payload) throws Exception {

        builder.append("GET")
                .append(" ")
                .append(" HTTP/1.0\r\n");


        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.append(header.getKey())
                    .append(" ")
                    .append(header.getValue())
                    .append("\r\n");
        }
        builder.append("\r\n");
        Socket socket = new Socket(url,80);
        socket.getOutputStream().write(
                builder.toString().getBytes(StandardCharsets.UTF_8)
        );
        socket.getOutputStream().flush();


        Reader reader  = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);


        String inputLine = null;
        Map<String,String> headersMap = new HashMap<>();
        StringBuffer responseString = new StringBuffer();


        response.setStatusText(bufferedReader.readLine());
        while(true) {
            inputLine = bufferedReader.readLine();
            System.out.println(inputLine);
            if(inputLine.isEmpty()) break;
            String[] headerParts = inputLine.split(": ");
            headersMap.put(headerParts[0],headerParts[1]);
        }

        response.setHeaders(headersMap);

        while ((inputLine = bufferedReader.readLine())!=null ){
            responseString.append(inputLine);
            responseString.append("\n");
        }


        bufferedReader.close();
        response.setPayload(responseString.toString());

        return response;
    }

    @Override
    public Response post(String url, Map<String, String> headers, String payload) {
        return null;
    }

    @Override
    public Response put(String url, Map<String, String> headers, byte[] payload) {
        return null;
    }

    @Override
    public Response put(String url, Map<String, String> headers, String payload) {
        return null;
    }

    @Override
    public Response delete(String url, Map<String, String> headers, byte[] payload) {
        return null;
    }

    @Override
    public Response delete(String url, Map<String, String> headers, String payload) {
        return null;
    }
}
