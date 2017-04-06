package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.Team;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.TeamRepository;

public class TeamRepositoryImpl implements TeamRepository {

    private final String URL = "http://207.154.199.94/api/teams";

    @Override
    public List<Team> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<Team>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public Team fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL + "/" + id).syncGetRequest();
            return mapper.readValue(response, Team.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }
}
