package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.restclient.dto.ReservationSlotDto;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

public class ReservationServiceRestClient {
    public static final String URL = "http://localhost:8084/reservation-service/api/reservation";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<ReservationSlotDto> getAvailable(String restaurantId, String beginDate, String endDate) throws IOException, URISyntaxException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri;
        if(!restaurantId.isEmpty())
            uri = UriComponentsBuilder.fromUriString(URL + "Slot/available-slots")
                    .queryParam("restaurantId", encodeUtf8(restaurantId))
                    .build()
                    .toUriString();
        else{
            uri = UriComponentsBuilder.fromUriString(URL + "Slot").build().toUriString();
        }
        System.out.println(uri);
        Request request = new Request.Builder()
                .url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, new TypeReference<List<ReservationSlotDto>>() {});
        }

        throw new RuntimeException();
    }

    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode(val, "UTF-8");
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public ReservationDto makeReservation(Long reservationSlotId, Long restaurantId, String status) throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ReservationDto reservationDto = new ReservationDto(getUser(ClientApp.getInstance().getToken()).getId(), reservationSlotId, restaurantId, status);
        @SuppressWarnings("deprecation")
        String jsonPayload = objectMapper.writeValueAsString(reservationDto);
        RequestBody body = RequestBody.create(JSON, jsonPayload);
        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException();
    }

    public List<ReservationDto> getReservations() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/reservationsOfClient/" + getUser(ClientApp.getInstance().getToken()).getId())
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, new TypeReference<List<ReservationDto>>() {});
        }

        throw new RuntimeException("Failed to fetch reservations, HTTP code: " + response.code());
    }

}
