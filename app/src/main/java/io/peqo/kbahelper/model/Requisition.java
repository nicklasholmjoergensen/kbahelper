package io.peqo.kbahelper.model;

import java.util.Date;

/**
 * Model class for a Requisition.
 * Contains information about bloodwork, assigned User and more.
 */

public class Requisition {

    final Long id;
    final int reqNum;
    final int runNum;
    final Date testTime;
    final boolean done;

    private Requisition(Builder builder) {
        this.id = builder.id;
        this.reqNum = builder.reqNum;
        this.runNum = builder.runNum;
        this.testTime = builder.testTime;
        this.done = builder.done;
    }

    static class Builder {
        private Long id;
        private int reqNum;
        private int runNum;
        private Date testTime;
        private boolean done;

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

        public Requisition build() {
            return new Requisition(this);
        }
    }

}
