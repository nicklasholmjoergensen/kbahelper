package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Bed;
import io.peqo.kbahelper.network.ApiConnection;

public class BedRepositoryImpl implements BedRepository {

    private final String URL = "http://207.154.199.94/api/beds";

    @Override
    public List<Bed> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL).syncRequest();
            return mapper.readValue(response, new TypeReference<List<Bed>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Bed fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Bed.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Bed bed) {

    }

    @Override
    public void delete(Long id) {

    }
}
