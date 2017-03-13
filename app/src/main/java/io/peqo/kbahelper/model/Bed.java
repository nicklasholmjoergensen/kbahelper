package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Class representing a bedNumber, which has a many-to-one relationship with a Room,
 * and a one-to-one relationship with a Patient.
 */

@JsonDeserialize(builder = Bed.Builder.class)
public final class Bed {

    public final Long id;
    public final int bedNumber;

    private Bed(Builder builder) {
        this.id = builder.id;
        this.bedNumber = builder.bedNumber;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private int bedNumber;

        public Builder() {}

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("bed_number")
        public Builder setBedNumber(int bedNumber) {
            this.bedNumber = bedNumber;
            return this;
        }

        public Bed build() {
            return new Bed(this);
        }
    }

}
