package io.peqo.kbahelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Department.Builder.class)
public final class Department {

    public final Long id;
    public final Long teamId;
    public final String name;

    private Department(Builder builder) {
        this.id = builder.id;
        this.teamId = builder.teamId;
        this.name = builder.name;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private Long id;
        private Long teamId;
        private String name;

        public Builder() {}

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

        public Department build() {
            return new Department(this);
        }
    }

}
