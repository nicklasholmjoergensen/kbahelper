package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = User.Builder.class)
public final class User {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String username;
    public final String email;

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("first_name")
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @JsonProperty("last_name")
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @JsonProperty("username")
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        @JsonProperty("email")
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
