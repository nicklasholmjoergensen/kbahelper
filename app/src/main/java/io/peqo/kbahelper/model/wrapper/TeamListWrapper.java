package io.peqo.kbahelper.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = TeamListWrapper.Builder.class)
public class TeamListWrapper {

    public final Long id;
    public final String name;
    public final int finishedReq;
    public final int totalReq;

    private TeamListWrapper(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.finishedReq = builder.finishedReq;
        this.totalReq = builder.totalReq;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Long id;
        private String name;
        private int finishedReq;
        private int totalReq;

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

        public TeamListWrapper build() {
            return new TeamListWrapper(this);
        }

    }

}
