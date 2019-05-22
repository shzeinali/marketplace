package com.marketplace.data.model;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class Category {
    public String icon;
    public String name;

    public Category(Builder builder) {
        this.icon = builder.icon;
        this.name = builder.name;
    }

    public static class Builder {
        private String name;
        private String icon;

        public Category build() {
            return new Category(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }
    }
}
