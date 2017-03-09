package io.peqo.kbahelper.model;

import java.util.Date;

/**
 * Model class for a Requisition.
 * Contains information about bloodwork, assigned User and more.
 */

public final class Requisition {

    public final Long id;
    public final int reqNum;
    public final int runNum;
    public final Date testTime;
    public final boolean done;
    public final Patient patient;

    private Requisition(Builder builder) {
        this.id = builder.id;
        this.reqNum = builder.reqNum;
        this.runNum = builder.runNum;
        this.testTime = builder.testTime;
        this.done = builder.done;
        this.patient = builder.patient;
    }

    public static class Builder {
        private Long id;
        private int reqNum;
        private int runNum;
        private Date testTime;
        private boolean done;
        private Patient patient;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setReqNum(int reqNum) {
            this.reqNum = reqNum;
            return this;
        }

        public Builder setRunNum(int runNum) {
            this.runNum = runNum;
            return this;
        }

        public Builder setTestTime(Date testTime) {
            this.testTime = testTime;
            return this;
        }

        public Builder setDone(boolean done) {
            this.done = done;
            return this;
        }

        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Requisition build() {
            return new Requisition(this);
        }
    }

    @Override
    public String toString() {
        return "Requisition{" +
                "id=" + id +
                ", reqNum=" + reqNum +
                ", runNum=" + runNum +
                ", testTime=" + testTime +
                ", done=" + done +
                ", patient=" + patient +
                '}';
    }
}
