package com.iluwatar.producer.consumer;

import lombok.Data;

@Data
public class Item {
    private int id;
    private String producer;

    public Item(int id, String producer) {
        this.id = id;
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                '}';
    }
}
