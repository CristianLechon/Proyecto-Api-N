package ec.edu.uce.ProyectoNasaMars.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarsData {
    private static final String API_KEY = "c1vdp816cGvkK9mZAsfyrljkyYvdJYAvtaDvUS7G";
    private static final String API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    public List<MarsPhoto> getAllMarsPhotos() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        List<MarsPhoto> allPhotos = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?sol=1000&api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        List<MarsPhoto> photos = gson.fromJson(jsonObject.getAsJsonArray("photos"), new TypeToken<List<MarsPhoto>>() {
        }.getType());

        allPhotos.addAll(photos);

        return Collections.unmodifiableList(allPhotos);
    }

}
