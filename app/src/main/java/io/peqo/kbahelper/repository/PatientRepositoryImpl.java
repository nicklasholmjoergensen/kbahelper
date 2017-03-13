package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.network.ApiConnection;

public class PatientRepositoryImpl implements PatientRepository {

    private final String URL = "http://207.154.199.94/api/patients";

    @Override
    public List<Patient> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL)
                    .syncRequest();
            return mapper.readValue(response, new TypeReference<List<Patient>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Patient fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.createGET(URL + "/" + id).syncRequest();
            return mapper.readValue(response, Patient.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public void save(Patient patient) {

    }

    @Override
    public void delete(Long id) {

    }
}
