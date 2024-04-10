# SponsoredAds
This is a coding assignment for Backend Developer position. Completed by Svyat Ayrishin
# To start the application loccaly
1. Download ZIP from GitHub
2. Unzip
3. Enter folder with unzipped project
4. run mvn clean package(all tests will be run)
5. in folder target there will be file SponsoredAds-0.0.1-SNAPSHOT.jar
6. run java -jar ponsoredAds-0.0.1-SNAPSHOT.jar
# Endpoints
1. POST http://localhost:8080/api/campaigns create a compaign.
   JSON for Postman {
  "name": "Summer Sale",
  "startDate": "2024-04-09T00:00:00",
  "productIds": [1, 2],
  "bid": 2.5
}
2. GET http://localhost:8080/api/ads api to retrieve ads Parameters:Category - a String representing category of product  
Example for Postman http://localhost:8080/api/ads?category=Electronics

# app uses this script to complete a products table
INSERT INTO product (title, category, price, serial_number) VALUES
('Laptop Pro 17', 'Electronics', 2399.99, 'SN001'),
('Smartphone X', 'Electronics', 599.99, 'SN002'),
('Coffee Maker Deluxe', 'Home Appliances', 149.99, 'SN003');
