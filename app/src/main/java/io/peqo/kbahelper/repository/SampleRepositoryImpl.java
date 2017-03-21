package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import io.peqo.kbahelper.model.Sample;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.SampleRepository;
import okhttp3.Response;

public class SampleRepositoryImpl implements SampleRepository {

    private final String URL = "http://207.154.199.94/api/samples";

    @Override
    public List<Sample> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<Sample>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public List<Sample> fetchAllFromId(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL + "/" + id).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<Sample>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Sample fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL + "/s/" + id).syncGetRequest();
            return mapper.readValue(response, Sample.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public int save(Sample sample) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(sample);
            Response response = ApiConnection.open(URL)
                    .syncPostRequest(json);
            if (response != null) return response.code();
            Log.d("DEBUG", json);
        } catch(IOException e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return -1;
    }

    @Override
    public void delete(Long id) {

    }
}
