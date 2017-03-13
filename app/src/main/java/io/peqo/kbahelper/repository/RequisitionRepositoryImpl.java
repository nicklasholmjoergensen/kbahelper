package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.network.ApiConnection;

/**
 * Concrete implementation of RequistionRepository.
 */

public class RequisitionRepositoryImpl implements RequisitionRepository {

    private final String URL = "http://207.154.199.94/api/req";

    @Override
    public List<Requisition> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL).syncRequest();
            return mapper.readValue(response, new TypeReference<List<Requisition>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Requisition fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Requisition.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Requisition requisition) {

    }

    @Override
    public void delete(Long id) {

    }
}
