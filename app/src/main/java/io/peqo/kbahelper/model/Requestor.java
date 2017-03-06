package io.peqo.kbahelper.model;

/**
 * Class modelling the department requesting the bloodwork.
 */

public class Requestor {

    final Long id;
    final String name;
    final String department;
    final String address;
    final String postalCode;
    final String country;

    private Requestor(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.department = builder.department;
        this.address = builder.address;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    static class Builder {
        private Long id;
        private String name;
        private String department;
        private String address;
        private String postalCode;
        private String country;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Requestor build() {
            return new Requestor(this);
        }
    }

}
