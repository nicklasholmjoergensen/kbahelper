package io.peqo.kbahelper.model;

/**
 * Wrapper class used only for displaying requisition and patient info
 * on the Requisition Overview.
 */

public class RequisitionListWrapper {

    public final String firstName;
    public final String lastName;
    public final String cprNum;
    public final String department;
    public final int room;
    public final int bed;

    private RequisitionListWrapper(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNum = builder.cprNum;
        this.department = builder.department;
        this.room = builder.room;
        this.bed = builder.bed;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String cprNum;
        private String department;
        private int room;
        private int bed;

        public Builder() {}

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

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setRoom(int room) {
            this.room = room;
            return this;
        }

        public Builder setBed(int bed) {
            this.bed = bed;
            return this;
        }

        public RequisitionListWrapper build() {
            return new RequisitionListWrapper(this);
        }
    }

}
