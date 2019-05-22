package com.marketplace.data.model;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */
public class Order {
    public static final int PENDING = 0;
    public static final int IN_PROGRESS = 1;
    public static final int DELIVERY = 2;
    public static final int DELIVERED = 3;

    public String icon;
    public String name;
    public String address;
    public int status;

    public Order(Builder builder) {
        this.icon = builder.icon;
        this.name = builder.name;
        this.address = builder.address;
        this.status = builder.status;
    }

    public static String getStatus(int id) {
        String str = "";
        switch (id) {
            case PENDING:
                str = "Pending";
                break;
            case IN_PROGRESS:
                str = "InProgress";
                break;
            case DELIVERY:
                str = "Delivery";
                break;
            case DELIVERED:
                str = "Delivered";
                break;
            default:
                break;
        }
        return str;
    }

    public static class Builder {
        private String name;
        private String icon;
        private String address;
        private int status;

        public Order build() {
            return new Order(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }
    }
}
