package io.peqo.kbahelper.model;

public class Sample {

    public final Long id;
    public final String name;
    public final boolean taken;

    private Sample(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.taken = builder.taken;
    }

    public static class Builder {
        private Long id;
        private String name;
        private boolean taken;

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTaken(boolean taken) {
            this.taken = taken;
            return this;
        }

        public Sample build() {
            return new Sample(this);
        }
    }

}
