package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dto.*;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

public class UserServiceRestClient {
    public static final String URL = "http://localhost:8084/user-service/api";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String username, String password) throws IOException {
        TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/loginUser")
                .post(body)
                .build();
        ;

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            // Ovde samo vraćamo string JWT tokena
            return response.body().string();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }


    public List<UserDto> getAllUsers() throws IOException, URISyntaxException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URL + "/users").build().toUriString();

        System.out.println(uri);
        Request request = new Request.Builder().url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get().build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, new TypeReference<List<UserDto>>() {});
        }
        throw new RuntimeException();
    }

    public void blockUser(String username, String email) throws IOException, URISyntaxException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URL + "/block")
                .queryParam("username", username)
                .queryParam("email", email)
                .build().toUriString();

        System.out.println(uri);
        Request request = new Request.Builder().url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(RequestBody.create(JSON, ""))
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());
    }

    public void unblockUser(String username, String email) throws IOException, URISyntaxException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URL + "/unblock")
                .queryParam("username", username)
                .queryParam("email", email)
                .build().toUriString();

        System.out.println(uri);
        Request request = new Request.Builder().url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(RequestBody.create(JSON, ""))
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        // Priprema tela zahteva
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        // Kreiranje HTTP zahteva
        Request request = new Request.Builder()
                .url(URL + "/registerClient")
                .post(body)
                .build();

        // Slanje zahteva
        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());

        if (response.code() == 201) {
            String responseBody = response.body().string();

            // Proverite da li je odgovor JWT ili JSON
            if (isJwt(responseBody)) {
                // Dekodirajte JWT payload
                String payload = decodeJwtPayload(responseBody);

                // Parsirajte payload u ClientDto
                return objectMapper.readValue(payload, ClientDto.class);
            } else {
                // Ako je odgovor JSON, direktno parsirajte
                return objectMapper.readValue(responseBody, ClientDto.class);
            }
        }

        throw new RuntimeException("Something went wrong");
    }

    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) throws IOException {
        // Priprema tela zahteva
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));

        // Kreiranje HTTP zahteva
        Request request = new Request.Builder()
                .url(URL + "/registerManager")
                .post(body)
                .build();

        // Slanje zahteva
        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());

        if (response.code() == 201) {
            String responseBody = response.body().string();

            // Proverite da li je odgovor JWT ili JSON
            if (isJwt(responseBody)) {
                // Dekodirajte JWT payload
                String payload = decodeJwtPayload(responseBody);

                // Parsirajte payload u ClientDto
                return objectMapper.readValue(payload, ManagerDto.class);
            } else {
                // Ako je odgovor JSON, direktno parsirajte
                return objectMapper.readValue(responseBody, ManagerDto.class);
            }
        }

        throw new RuntimeException("Something went wrong");
    }
    // Provera da li je odgovor JWT
    private boolean isJwt(String responseBody) {
        return responseBody.split("\\.").length == 3;
    }

    // Dekodiranje payload-a JWT-a
    private String decodeJwtPayload(String jwt) {
        String[] parts = jwt.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT format");
        }

        // Dekodirajte drugi deo (payload)
        return new String(Base64.getUrlDecoder().decode(parts[1]));
    }

}
