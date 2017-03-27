package io.peqo.kbahelper.model;

import java.util.List;

public class User {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String username;
    public final String password;
    public final List<Requisition> requisitions;

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.password = builder.password;
        this.requisitions = builder.requisitions;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private List<Requisition> requisitions;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRequisitions(List<Requisition> requisitions) {
            this.requisitions = requisitions;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
