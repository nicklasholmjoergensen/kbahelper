package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.RequestorRepository;

public class RequestorRepositoryImpl implements RequestorRepository {

    private final String URL = "http://207.154.199.94/api/requestor";

    @Override
    public List<Requestor> fetchAll() {
        return null;
    }

    @Override
    public Requestor fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Requestor.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Requestor requestor) {

    }

    @Override
    public void delete(Long id) {

    }
}
