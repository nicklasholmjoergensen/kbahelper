package io.peqo.kbahelper.model.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeptListWrapper.Builder.class)
public class DeptListWrapper {

    public final Long id;
    public final int unfinishedReq;
    public final int totalReq;

    private DeptListWrapper(Builder builder) {
        this.id = builder.id;
        this.unfinishedReq = builder.unfinishedReq;
        this.totalReq = builder.totalReq;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Long id;
        private int unfinishedReq;
        private int totalReq;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUnfinishedReq(int unfinishedReq) {
            this.unfinishedReq = unfinishedReq;
            return this;
        }

        public Builder setTotalReq(int totalReq) {
            this.totalReq = totalReq;
            return this;
        }

        public DeptListWrapper build() {
            return new DeptListWrapper(this);
        }
    }
}
