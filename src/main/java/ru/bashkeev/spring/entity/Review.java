package ru.bashkeev.spring.entity;

import lombok.Getter;

@Getter
public class Review {
    private final String text;
    private final int rating;

    public Review(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("Review{text='%s', rating=%d}", text, rating);
    }
}
