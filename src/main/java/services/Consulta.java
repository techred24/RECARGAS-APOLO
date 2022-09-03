package services;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Consulta {
    // https://demo.bustrack.mx/apsmg/api/config/tarjeta/lector
    private static String api = "https://demo.bustrack.mx/apsmg/api/";
    private static String token = "";
    public Consulta (String token) {
        this.token = token;
    }
    /*public static void main(String[] args) throws Exception {
        System.out.println("Working!");
        Map<String,Object> map = new HashMap<>();
        //You can convert any Object.
        String[] value1 = new String[] { "value11", "value12", "value13" };
        String[] value2 = new String[] { "value21", "value22", "value23" };
        map.put("key1", value1);
        map.put("key2", value2);
        map.put("key3","string1");
        map.put("key4","string2");

        String json = new ObjectMapper().writeValueAsString(map);
        System.out.println(json);

        Map<Object, Object> data = new HashMap<>();
        data.put("usuario", "vendedordorado");
        data.put("contrasena", "Ventas111");
        Map<Object, Object> respuesta = Consulta.sendPost("usuario/loginV2/", data);
        System.out.println(respuesta.get("data"));


        //Map<Object, Object> respuesta = Consulta.sendGet("config/tarjeta/lector");
        //System.out.println(respuesta);
    }*/
    public static Map<Object, Object> sendGet(String consulta) throws Exception {
        Map<Object, Object> respuesta = new HashMap<>();
        final String url = api + consulta ;
        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                //.uri(URI.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"))
                .uri(URI.create(url))
                .header("content-type", "application/json")
                .header("authorization", "Bearer " + token)
                //.setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        short statusCode = (short) response.statusCode();

        try {
            if (statusCode == 200) {
                ObjectMapper mapper = new ObjectMapper();
                Map<Object, Object> data = mapper.readValue(response.body(), Map.class);
                // print status code
                //System.out.println(response.statusCode());
                // print response body
                //System.out.println(response.body());

                respuesta.put("code", (short)response.statusCode());
                respuesta.put("data", data);
            } else {
                respuesta.put("code", (short)response.statusCode());
            }
        } catch (Exception e) {
            respuesta.put("code", (short)response.statusCode());
            e.printStackTrace();
        } finally {
            return respuesta;
        }
    }

    public static Map<Object, Object> sendPost(String consulta, Map<Object, Object> body) throws Exception {
        Map<Object, Object> respuesta = new HashMap<>();
        final String url = api + consulta ;
        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(body))
                .uri(URI.create(url))
                //.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                //.header("content-type", "application/json")
                .header("authorization", "Bearer " + token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        //System.out.println(response.statusCode());
        // print response body
        //System.out.println(response.body());
        short statusCode = (short)response.statusCode();
        //System.out.println(statusCode);
        try {
            if (statusCode == 200) {
                ObjectMapper mapper = new ObjectMapper();
                Map<Object, Object> data = mapper.readValue(response.body(), Map.class);
                //Map<Object, Object> another = (Map<Object, Object>) map.get("usuario");
                //System.out.println(another.get("activo").getClass().getName());
                respuesta.put("code", (short)response.statusCode());
                respuesta.put("data", data);
            } else {
                respuesta.put("code", (short)response.statusCode());
            }
        } catch (IOException e) {
            respuesta.put("code", (short)response.statusCode());
            System.out.println("In here. Catch");
            e.printStackTrace();
        } finally {
            return respuesta;
        }
    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        //System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}