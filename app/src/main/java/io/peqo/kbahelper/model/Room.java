package io.peqo.kbahelper.model;

/**
 * Class modelling a room with x-number of patients.
 */

public class Room {

    final Long id;
    final int roomNumber;

    private Room(Builder builder) {
        this.id = builder.id;
        this.roomNumber = builder.roomNumber;
    }

    static class Builder {
        private Long id;
        private int roomNumber;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder roomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

}
