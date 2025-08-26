# CryptoDash API - Spring Boot Version

🚀 **A comprehensive cryptocurrency price tracking and investment analysis API built with Spring Boot and Java**

## 🎯 Overview

This is the **Spring Boot version** of the CryptoDash API - a modern, reactive web application that provides real-time cryptocurrency prices, investment analysis, and portfolio calculations. This version replicates all functionality from the original Node.js implementation while leveraging Spring Boot's robust ecosystem.

## ✨ Features

- 📈 **Real-time Crypto Prices**: Live cryptocurrency price data from CoinGecko API
- 📊 **7-Day Price History**: Comprehensive price trend analysis and visualization
- 💰 **Portfolio Calculator**: Calculate potential returns on cryptocurrency investments
- 🎯 **Best Buy Analysis**: AI-powered recommendations for optimal buying opportunities
- 🎨 **Modern UI**: Beautiful purple gradient interface with responsive design
- ⚡ **Reactive Programming**: Built with Spring WebFlux for high-performance async operations
- 🔄 **RESTful API**: Clean, well-documented API endpoints

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.1.0, Spring WebFlux
- **Language**: Java 21
- **Build Tool**: Maven
- **HTTP Client**: WebClient (reactive)
- **API Integration**: CoinGecko API v3
- **Frontend**: Vanilla HTML5, CSS3, JavaScript
- **Architecture**: MVC pattern with reactive programming

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/cryptodash/cryptoapi/
│   │   ├── CryptoApiApplication.java      # Main application class
│   │   ├── controller/
│   │   │   └── CryptoController.java      # REST API endpoints
│   │   ├── service/
│   │   │   └── CryptoService.java         # Business logic layer
│   │   └── model/
│   │       ├── CryptoInfoResponse.java    # Response model
│   │       ├── PriceHistory.java          # Price history model
│   │       └── PricePoint.java            # Individual price point
│   └── resources/
│       ├── static/
│       │   └── index.html                 # Frontend UI
│       └── application.properties         # Configuration
├── target/                                # Compiled files (ignored)
└── pom.xml                               # Maven dependencies
```

## 🚀 Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Git

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Ezn605/SpringBoot-CryptoAPI.git
   cd SpringBoot-CryptoAPI
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Open your browser and navigate to: `http://localhost:8080`
   - The CryptoDash interface will load automatically

## 📡 API Endpoints

### Get Cryptocurrency Information
```http
GET /crypto-info?coin={coinId}&portfolio={amount}
```

**Parameters:**
- `coin` (required): Cryptocurrency ID (e.g., "bitcoin", "ethereum", "cardano")
- `portfolio` (optional): Investment amount in USD for portfolio calculations

**Response:**
```json
{
  "coin": "bitcoin",
  "currentPrice": 115840.82,
  "priceChange7d": 2.5,
  "priceHistory": {
    "labels": ["Day 1", "Day 2", ...],
    "prices": [115000, 115500, ...]
  },
  "portfolioValue": 1158.41,
  "portfolioGain": 25.00,
  "investmentTip": "Strong bullish momentum detected..."
}
```

## 🎨 UI Features

- **Cryptocurrency Selection**: Dropdown with popular cryptocurrencies
- **Live Price Display**: Real-time price updates with 7-day change percentage
- **Interactive Charts**: Visual price history representation
- **Portfolio Calculator**: Input investment amount for return calculations
- **Investment Insights**: AI-generated buy/sell recommendations
- **Responsive Design**: Works seamlessly on desktop and mobile devices

## 🔧 Configuration

The application can be configured via `application.properties`:

```properties
# Server configuration
server.port=8080

# Logging configuration  
logging.level.com.cryptodash=DEBUG
logging.level.org.springframework.web.reactive=DEBUG

# External API configuration
coingecko.api.base-url=https://api.coingecko.com/api/v3
```

## 🏗️ Architecture

### Reactive Programming Model
This application uses Spring WebFlux for non-blocking, reactive programming:

- **WebClient**: For asynchronous HTTP calls to CoinGecko API
- **Mono/Flux**: Reactive types for handling asynchronous data streams
- **Async Controllers**: Non-blocking request handling

### Layered Architecture
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic and external API integration
- **Model Layer**: Data transfer objects and domain models

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

## 📦 Build for Production

Create a production-ready JAR:
```bash
mvn clean package
java -jar target/crypto-api-0.0.1-SNAPSHOT.jar
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [CoinGecko API](https://www.coingecko.com/en/api) for providing free cryptocurrency data
- [Spring Boot](https://spring.io/projects/spring-boot) for the excellent framework
- [Maven](https://maven.apache.org/) for dependency management

## 📞 Support

If you encounter any issues or have questions, please [open an issue](https://github.com/Ezn605/SpringBoot-CryptoAPI/issues) on GitHub.

---

⭐ **Star this repository if you found it helpful!**
