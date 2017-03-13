package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Class for representing a patient.
 */

@JsonDeserialize(builder = Patient.Builder.class)
public final class Patient {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String cprNum;
    public final int status;

    private Patient(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNum = builder.cprNum;
        this.status = builder.status;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String cprNum;
        private int status;

        public Builder() {}

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

        @JsonProperty("cpr_num")
        public Builder setCprNum(String cprNum) {
            this.cprNum = cprNum;
            return this;
        }

        @JsonProperty("status")
        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Patient build() {
            return new Patient(this);
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cprNum='" + cprNum + '\'' +
                ", status=" + status +
                '}';
    }
}
