package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

/**
 * Model class for a Requisition.
 * Contains information about bloodwork, assigned User and more.
 */

@JsonDeserialize(builder = Requisition.Builder.class)
public final class Requisition {

    public final Long id;
    public final int reqNum;
    public final int runNum;
    public final Date testTime;
    public final int status;
    public final int patientId;
    public final int requestorId;

    private Requisition(Builder builder) {
        this.id = builder.id;
        this.reqNum = builder.reqNum;
        this.runNum = builder.runNum;
        this.testTime = builder.testTime;
        this.status = builder.status;
        this.patientId = builder.patientId;
        this.requestorId = builder.requestorId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private int reqNum;
        private int runNum;
        private Date testTime;
        private int status;
        private int patientId;
        private int requestorId;

        public Builder() {}

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("req_num")
        public Builder setReqNum(int reqNum) {
            this.reqNum = reqNum;
            return this;
        }

        @JsonProperty("run_num")
        public Builder setRunNum(int runNum) {
            this.runNum = runNum;
            return this;
        }

        @JsonProperty("test_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        public Builder setTestTime(Date testTime) {
            this.testTime = testTime;
            return this;
        }

        @JsonProperty("status")
        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        @JsonProperty("patient_id")
        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        @JsonProperty("requestor_id")
        public Builder setRequestorId(int requestorId) {
            this.requestorId = requestorId;
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
                ", status=" + status +
                ", patientId=" + patientId +
                ", requestorId=" + requestorId +
                '}';
    }
}
