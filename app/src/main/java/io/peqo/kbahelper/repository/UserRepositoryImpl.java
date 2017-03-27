package io.peqo.kbahelper.repository;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.peqo.kbahelper.model.User;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.contract.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private final String URL = "http://207.154.199.94/api/users";

    @Override
    public List<User> fetchAll() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL).syncGetRequest();
            return mapper.readValue(response, new TypeReference<List<User>>(){});
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }

    @Override
    public User fetchObject(Long id) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String response = ApiConnection.open(URL + "/" + id).syncGetRequest();
            return mapper.readValue(response, User.class);
        } catch(Exception e) {
            Log.d("DEBUG", "Error: " + e);
        }
        return null;
    }
}
