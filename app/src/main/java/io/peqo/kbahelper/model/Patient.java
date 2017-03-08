package io.peqo.kbahelper.model;

/**
 * Class for representing a patient.
 */

public class Patient {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String cprNum;
    public final boolean registered;

    private Patient(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNum = builder.cprNum;
        this.registered = builder.registered;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String cprNum;
        private boolean registered;

        public Builder() {}

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

        public Builder setCprNum(String cprNum) {
            this.cprNum = cprNum;
            return this;
        }

        public Builder setRegistered(boolean registered) {
            this.registered = registered;
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
                ", registered=" + registered +
                '}';
    }
}
