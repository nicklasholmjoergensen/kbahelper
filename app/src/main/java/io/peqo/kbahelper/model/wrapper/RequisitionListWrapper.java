package io.peqo.kbahelper.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Wrapper class for requisition overview.
 */

@JsonDeserialize(builder = RequisitionListWrapper.Builder.class)
public class RequisitionListWrapper {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String cprNumber;
    public final String department;
    public final int roomNumber;
    public final int bedNumber;

    private RequisitionListWrapper(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNumber = builder.cprNumber;
        this.department = builder.department;
        this.roomNumber = builder.roomNumber;
        this.bedNumber = builder.bedNumber;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String cprNumber;
        private String department;
        private int roomNumber;
        private int bedNumber;

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

        @JsonProperty("cpr_number")
        public Builder setCprNumber(String cprNumber) {
            this.cprNumber = cprNumber;
            return this;
        }

        @JsonProperty("dept_name")
        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        @JsonProperty("room_number")
        public Builder setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        @JsonProperty("bed_number")
        public Builder setBedNumber(int bedNumber) {
            this.bedNumber = bedNumber;
            return this;
        }

        public RequisitionListWrapper build() {
            return new RequisitionListWrapper(this);
        }
    }

}
