package com.marketplace.data.model;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class Product {
    public String icon;
    public String name;

    public Product(Builder builder) {
        this.icon = builder.icon;
        this.name = builder.name;
    }

    public static class Builder {
        private String name;
        private String icon;

        public Product build() {
            return new Product(this);
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
