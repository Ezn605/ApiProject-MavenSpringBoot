package com.cryptodash.cryptoapi.model;

public class PricePoint {
    private Long timestamp;
    private Double price;
    private String label;
    private String date;

    // Constructors
    public PricePoint() {}

    public PricePoint(Long timestamp, Double price, String label, String date) {
        this.timestamp = timestamp;
        this.price = price;
        this.label = label;
        this.date = date;
    }

    // Getters and Setters
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
