package io.peqo.kbahelper.model.wrapper;

/**
 * Wrapper class for requisition overview.
 */

public class RequisitionOverview {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String cprNumber;
    public final String department;
    public final int roomNumber;
    public final int bedNumber;

    private RequisitionOverview(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNumber = builder.cprNumber;
        this.department = builder.department;
        this.roomNumber = builder.roomNumber;
        this.bedNumber = builder.bedNumber;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String cprNumber;
        private String department;
        private int roomNumber;
        private int bedNumber;

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

        public Builder setCprNumber(String cprNumber) {
            this.cprNumber = cprNumber;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setBedNumber(int bedNumber) {
            this.bedNumber = bedNumber;
            return this;
        }

        public RequisitionOverview build() {
            return new RequisitionOverview(this);
        }
    }

}
