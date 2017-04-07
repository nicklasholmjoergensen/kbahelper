package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Team.Builder.class)
public class Team {

    public final Long id;
    public final String name;
    public final int numUsers;

    private Team(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.numUsers = builder.numUsers;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private String name;
        private int numUsers;

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("num_users")
        public Builder setNumUsers(int numUsers) {
            this.numUsers = numUsers;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

}
