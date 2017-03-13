package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Class modelling a roomNumber with x-number of patients.
 */

@JsonDeserialize(builder = Room.Builder.class)
public final class Room {

    public final Long id;
    public final int roomNumber;

    private Room(Builder builder) {
        this.id = builder.id;
        this.roomNumber = builder.roomNumber;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private int roomNumber;

        public Builder() {}

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("room_number")
        public Builder roomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

}
