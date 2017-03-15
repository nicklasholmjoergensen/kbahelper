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
    public final Long patientId;
    public final Long requestorId;
    public final int reqNum;
    public final int runNum;
    public final int status;
    public final Date orderDate;
    public final Date fulfilledDate;
    public final String description;

    private Requisition(Builder builder) {
        this.id = builder.id;
        this.reqNum = builder.reqNum;
        this.runNum = builder.runNum;
        this.orderDate = builder.orderDate;
        this.fulfilledDate = builder.fulfilledDate;
        this.status = builder.status;
        this.description = builder.description;
        this.patientId = builder.patientId;
        this.requestorId = builder.requestorId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private Long patientId;
        private Long requestorId;
        private int reqNum;
        private int runNum;
        private int status;
        private Date orderDate;
        private Date fulfilledDate;
        private String description;

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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        public Builder setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        @JsonProperty("fulfilled_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        public Builder setFullfilledDate(Date fulfilledDate) {
            this.fulfilledDate = fulfilledDate;
            return this;
        }

        @JsonProperty("status")
        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        @JsonProperty("patient_id")
        public Builder setPatientId(Long patientId) {
            this.patientId = patientId;
            return this;
        }

        @JsonProperty("requestor_id")
        public Builder setRequestorId(Long requestorId) {
            this.requestorId = requestorId;
            return this;
        }

        @JsonProperty("description")
        public Builder setDescription(String description) {
            this.description = description;
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
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", patientId=" + patientId +
                ", requestorId=" + requestorId +
                '}';
    }
}
