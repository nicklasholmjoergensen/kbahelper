package io.peqo.kbahelper.model.wrapper;

/**
 * Wrapper class used only for displaying requisition and patient info
 * on the Requisition Overview.
 */

public class RequisitionListWrapper {

    public final Long id;
    public final String firstName;
    public final String lastName;
    public final String cprNum;
    public final String deptName;
    public final int roomNumber;
    public final int bedNumber;

    private RequisitionListWrapper(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cprNum = builder.cprNum;
        this.deptName = builder.deptName;
        this.roomNumber = builder.roomNumber;
        this.bedNumber = builder.bedNumber;
    }

    public static class Builder {

        private Long id;
        private String firstName;
        private String lastName;
        private String cprNum;
        private String deptName;
        private int roomNumber;
        private int bedNumber;

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

        public Builder setDeptName(String deptName) {
            this.deptName = deptName;
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

        public RequisitionListWrapper build() {
            return new RequisitionListWrapper(this);
        }
    }

}
