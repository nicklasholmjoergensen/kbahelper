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
    public final Long patientId;
    public final Long roomId;

    private Bed(Builder builder) {
        this.id = builder.id;
        this.bedNumber = builder.bedNumber;
        this.patientId = builder.patientId;
        this.roomId = builder.roomId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private int bedNumber;
        private Long patientId;
        private Long roomId;

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

        @JsonProperty("patient_id")
        public Builder setPatientId(Long patientId) {
            this.patientId = patientId;
            return this;
        }

        @JsonProperty("room_id")
        public Builder setRoomId(Long roomId) {
            this.roomId = roomId;
            return this;
        }

        public Bed build() {
            return new Bed(this);
        }
    }

}
