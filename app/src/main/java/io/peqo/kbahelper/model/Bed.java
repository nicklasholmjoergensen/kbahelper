package io.peqo.kbahelper.model;

/**
 * Class representing a bedNumber, which has a many-to-one relationship with a Room,
 * and a one-to-one relationship with a Patient.
 */

public class Bed {

    public final Long id;
    public final int bedNumber;

    private Bed(Builder builder) {
        this.id = builder.id;
        this.bedNumber = builder.bedNumber;
    }

    public static class Builder {
        private Long id;
        private int bedNumber;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder bedNumber(int bedNumber) {
            this.bedNumber = bedNumber;
            return this;
        }

        public Bed build() {
            return new Bed(this);
        }
    }

}
