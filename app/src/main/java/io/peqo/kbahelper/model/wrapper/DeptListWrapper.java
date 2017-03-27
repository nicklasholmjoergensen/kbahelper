package io.peqo.kbahelper.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeptListWrapper.Builder.class)
public class DeptListWrapper {

    public final Long id;
    public final String name;
    public final int unfinishedReq;
    public final int totalReq;

    private DeptListWrapper(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.unfinishedReq = builder.unfinishedReq;
        this.totalReq = builder.totalReq;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Long id;
        private String name;
        private int unfinishedReq;
        private int totalReq;

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("dept_name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("unfinished_req")
        public Builder setUnfinishedReq(int unfinishedReq) {
            this.unfinishedReq = unfinishedReq;
            return this;
        }

        @JsonProperty("finished_req")
        public Builder setTotalReq(int totalReq) {
            this.totalReq = totalReq;
            return this;
        }

        public DeptListWrapper build() {
            return new DeptListWrapper(this);
        }
    }
}
