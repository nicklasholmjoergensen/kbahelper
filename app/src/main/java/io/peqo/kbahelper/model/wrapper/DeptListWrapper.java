package io.peqo.kbahelper.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeptListWrapper.Builder.class)
public class DeptListWrapper {

    public final Long id;
    public final Long teamId;
    public final String name;
    public final int finishedReq;
    public final int totalReq;

    private DeptListWrapper(Builder builder) {
        this.id = builder.id;
        this.teamId = builder.teamId;
        this.name = builder.name;
        this.finishedReq = builder.finishedReq;
        this.totalReq = builder.totalReq;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private Long teamId;
        private String name;
        private int finishedReq;
        private int totalReq;

        @JsonProperty("id")
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        @JsonProperty("team_id")
        public Builder setTeamId(Long teamId) {
            this.teamId = teamId;
            return this;
        }

        @JsonProperty("dept_name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonProperty("finished_req")
        public Builder setFinishedReq(int finishedReq) {
            this.finishedReq = finishedReq;
            return this;
        }

        @JsonProperty("total_req")
        public Builder setTotalReq(int totalReq) {
            this.totalReq = totalReq;
            return this;
        }

        public DeptListWrapper build() {
            return new DeptListWrapper(this);
        }
    }
}
