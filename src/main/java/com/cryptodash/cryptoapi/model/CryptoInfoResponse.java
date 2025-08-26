package com.cryptodash.cryptoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CryptoInfoResponse {
    private String coin;
    private Double price;
    @JsonProperty("price_change_7d")
    private Double priceChange7d;
    private String currency;
    private String tip;
    @JsonProperty("price_history")
    private PriceHistory priceHistory;
    
    // Portfolio fields
    @JsonProperty("investment_amount")
    private Double investmentAmount;
    @JsonProperty("coins_bought")
    private Double coinsBought;
    @JsonProperty("current_value")
    private Double currentValue;
    @JsonProperty("portfolio_gain")
    private Double portfolioGain;
    @JsonProperty("portfolio_gain_percentage")
    private Double portfolioGainPercentage;

    // Constructors
    public CryptoInfoResponse() {}

    public CryptoInfoResponse(String coin, Double price, Double priceChange7d, String currency, String tip, PriceHistory priceHistory) {
        this.coin = coin;
        this.price = price;
        this.priceChange7d = priceChange7d;
        this.currency = currency;
        this.tip = tip;
        this.priceHistory = priceHistory;
    }

    // Getters and Setters
    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getPriceChange7d() { return priceChange7d; }
    public void setPriceChange7d(Double priceChange7d) { this.priceChange7d = priceChange7d; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public PriceHistory getPriceHistory() { return priceHistory; }
    public void setPriceHistory(PriceHistory priceHistory) { this.priceHistory = priceHistory; }
    
    public Double getInvestmentAmount() { return investmentAmount; }
    public void setInvestmentAmount(Double investmentAmount) { this.investmentAmount = investmentAmount; }
    
    public Double getCoinsBought() { return coinsBought; }
    public void setCoinsBought(Double coinsBought) { this.coinsBought = coinsBought; }
    
    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double currentValue) { this.currentValue = currentValue; }
    
    public Double getPortfolioGain() { return portfolioGain; }
    public void setPortfolioGain(Double portfolioGain) { this.portfolioGain = portfolioGain; }
    
    public Double getPortfolioGainPercentage() { return portfolioGainPercentage; }
    public void setPortfolioGainPercentage(Double portfolioGainPercentage) { this.portfolioGainPercentage = portfolioGainPercentage; }
}
