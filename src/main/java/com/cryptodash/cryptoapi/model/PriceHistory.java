package com.cryptodash.cryptoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PriceHistory {
    private String period;
    @JsonProperty("price_points")
    private List<PricePoint> pricePoints;
    @JsonProperty("total_data_points")
    private Integer totalDataPoints;
    private String note;

    // Constructors
    public PriceHistory() {}

    public PriceHistory(String period, List<PricePoint> pricePoints, Integer totalDataPoints, String note) {
        this.period = period;
        this.pricePoints = pricePoints;
        this.totalDataPoints = totalDataPoints;
        this.note = note;
    }

    // Getters and Setters
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public List<PricePoint> getPricePoints() { return pricePoints; }
    public void setPricePoints(List<PricePoint> pricePoints) { this.pricePoints = pricePoints; }

    public Integer getTotalDataPoints() { return totalDataPoints; }
    public void setTotalDataPoints(Integer totalDataPoints) { this.totalDataPoints = totalDataPoints; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
