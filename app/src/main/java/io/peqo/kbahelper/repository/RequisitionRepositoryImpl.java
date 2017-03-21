package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.RequisitionRepository;
import okhttp3.Response;

/**
 * Concrete implementation of RequistionRepository.
 */

public class RequisitionRepositoryImpl implements RequisitionRepository {

    private final String URL = "http://207.154.199.94/api/req";

    @Override
    public List<Requisition> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<Requisition>>(){});
        } catch(IOException e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Requisition fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL + "/" + id).syncGetRequest();
            return mapper.readValue(response, Requisition.class);
        } catch(IOException e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public int save(Requisition requisition) {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
        try {
            String json = mapper.writeValueAsString(requisition);
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
    public int delete(Long id) {
        return 0;
    }
}
