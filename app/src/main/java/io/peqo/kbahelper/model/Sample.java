package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Room.Builder.class)
public final class Sample {

    public final Long id;
    public final String name;
    public final int status;
    public final Long requisitionId;

    private Sample(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.status = builder.status;
        this.requisitionId = builder.requisitionId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private String name;
        private int status;
        private Long requisitionId;

        public Builder() {}

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("status")
        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        @JsonProperty("requisition_id")
        public Builder setRequisitionId(Long requisitionId) {
            this.requisitionId = requisitionId;
            return this;
        }

        public Sample build() {
            return new Sample(this);
        }
    }

}
