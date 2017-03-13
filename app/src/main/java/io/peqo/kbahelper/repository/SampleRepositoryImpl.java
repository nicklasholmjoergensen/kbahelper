package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Sample;
import io.peqo.kbahelper.network.ApiConnection;

/**
 * Created by Nicklas on 13-03-2017.
 */

public class SampleRepositoryImpl implements SampleRepository {

    private final String URL = "http://207.154.199.94/api/samples";

    @Override
    public List<Sample> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL).syncRequest();
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
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Sample.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Sample sample) {

    }

    @Override
    public void delete(Long id) {

    }
}