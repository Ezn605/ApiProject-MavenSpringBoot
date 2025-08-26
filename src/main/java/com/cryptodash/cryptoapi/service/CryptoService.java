package com.cryptodash.cryptoapi.service;

import com.cryptodash.cryptoapi.model.CryptoInfoResponse;
import com.cryptodash.cryptoapi.model.PriceHistory;
import com.cryptodash.cryptoapi.model.PricePoint;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CryptoService {

    private final WebClient webClient;
    private final List<String> investmentTips;

    public CryptoService() {
        this.webClient = WebClient.builder()
            .baseUrl("https://api.coingecko.com/api/v3")
            .build();

        this.investmentTips = Arrays.asList(
            "Never invest more than you can afford to lose.",
            "Diversify your portfolio to reduce risk.",
            "Do your own research before investing.",
            "Beware of market volatility.",
            "Consider long-term holding over short-term trading.",
            "Don't follow hype blindly; check fundamentals.",
            "Set clear investment goals and stick to them.",
            "Use stop-loss orders to manage risk.",
            "Keep emotions out of investment decisions.",
            "Stay updated with crypto news and regulations."
        );
    }

    public Mono<CryptoInfoResponse> getCryptoInfo(String coin, Double investmentAmount) {
        return webClient.get()
            .uri("/coins/{coin}/market_chart?vs_currency=usd&days=7", coin)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .delayElement(Duration.ofMillis(1500)) // Rate limiting delay
            .map(response -> processCryptoData(coin, response, investmentAmount))
            .onErrorReturn(createErrorResponse(coin));
    }

    private CryptoInfoResponse processCryptoData(String coin, JsonNode response, Double investmentAmount) {
        try {
            JsonNode pricesArray = response.get("prices");
            if (pricesArray == null || !pricesArray.isArray() || pricesArray.size() == 0) {
                return createErrorResponse(coin);
            }

            // Get current price from latest data point
            JsonNode latestPrice = pricesArray.get(pricesArray.size() - 1);
            Double currentPrice = latestPrice.get(1).asDouble();

            // Get oldest price for 7-day change calculation
            JsonNode oldestPrice = pricesArray.get(0);
            Double oldPrice = oldestPrice.get(1).asDouble();
            Double priceChange7d = ((currentPrice - oldPrice) / oldPrice) * 100;

            // Create price history
            PriceHistory priceHistory = createPriceHistory(pricesArray, currentPrice);

            // Get random investment tip
            String tip = investmentTips.get(new Random().nextInt(investmentTips.size()));

            CryptoInfoResponse response1 = new CryptoInfoResponse(coin, currentPrice, priceChange7d, "usd", tip, priceHistory);
            
            // Add portfolio calculation if investment amount is provided
            if (investmentAmount != null && investmentAmount > 0) {
                // Find lowest price in the 7-day period
                double lowestPrice = currentPrice;
                for (int i = 0; i < pricesArray.size(); i++) {
                    double price = pricesArray.get(i).get(1).asDouble();
                    if (price < lowestPrice) {
                        lowestPrice = price;
                    }
                }
                
                double coinsBought = investmentAmount / lowestPrice;
                double currentValue = coinsBought * currentPrice;
                double portfolioGain = currentValue - investmentAmount;
                double portfolioGainPercentage = (portfolioGain / investmentAmount) * 100;
                
                response1.setInvestmentAmount(investmentAmount);
                response1.setCoinsBought(coinsBought);
                response1.setCurrentValue(currentValue);
                response1.setPortfolioGain(portfolioGain);
                response1.setPortfolioGainPercentage(portfolioGainPercentage);
            }
            
            return response1;

        } catch (Exception e) {
            return createErrorResponse(coin);
        }
    }

    private PriceHistory createPriceHistory(JsonNode pricesArray, Double currentPrice) {
        List<PricePoint> pricePoints = new ArrayList<>();
        int totalPoints = pricesArray.size();
        int interval = Math.max(1, totalPoints / 6); // Get 6 historical points + current

        // Add historical points
        for (int i = 0; i < totalPoints - 1; i += interval) {
            if (i < totalPoints - 1) {
                JsonNode priceData = pricesArray.get(i);
                Long timestamp = priceData.get(0).asLong();
                Double price = priceData.get(1).asDouble();

                long daysAgo = Duration.between(
                    Instant.ofEpochMilli(timestamp),
                    Instant.now()
                ).toDays();

                String label;
                if (daysAgo == 0) {
                    label = "Earlier Today";
                } else if (daysAgo == 1) {
                    label = "1d ago";
                } else {
                    label = daysAgo + "d ago";
                }

                LocalDate date = Instant.ofEpochMilli(timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

                pricePoints.add(new PricePoint(timestamp, 
                    Math.round(price * 100.0) / 100.0, 
                    label, 
                    date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
            }
        }

        // Add current price point
        JsonNode latestPriceData = pricesArray.get(pricesArray.size() - 1);
        Long latestTimestamp = latestPriceData.get(0).asLong();
        LocalDate latestDate = Instant.ofEpochMilli(latestTimestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        pricePoints.add(new PricePoint(latestTimestamp,
            Math.round(currentPrice * 100.0) / 100.0,
            "Now",
            latestDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));

        return new PriceHistory("7 days", 
            pricePoints.subList(0, Math.min(7, pricePoints.size())), 
            totalPoints, 
            "Current price is always from the latest data point");
    }

    private CryptoInfoResponse createErrorResponse(String coin) {
        return new CryptoInfoResponse(coin, 0.0, 0.0, "usd", 
            "Unable to fetch price data. Please try again later.", 
            new PriceHistory("7 days", new ArrayList<>(), 0, "Error occurred"));
    }
}
