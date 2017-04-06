package io.peqo.kbahelper.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = Team.Builder.class)
public class Team {

    public final Long id;
    private List<User> users;

    public Team(Builder builder) {
        this.id = builder.id;
        this.users = new ArrayList<>();
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

}
