XML Gateway Service
A SOAP-to-REST gateway service that acts as an intermediary between legacy SOAP systems and modern REST APIs. The service receives SOAP requests, processes them through business logic, and calls external REST APIs, returning SOAP-formatted responses.

🏗️ Architecture
text
SOAP Request → GatewayEndpoint → MainProcessorService → ServiceRouter → TypeService → RestInvocation → REST API
      ↓                ↓                 ↓                  ↓               ↓               ↓           ↓
SOAP Response ← XmlResponse ← ResponseDTO ← ResponseDTO ← ResponseDTO ← ResponseEntity ← HTTP Response

Core Components
Component	Responsibility	Location
GatewayEndpoint	SOAP endpoint handler	controller/
MainProcessorService	Request orchestrator	service/
ServiceRouter	Service dispatcher	service/
TypeService	Business service contract	Interface
AccountService, CardService, etc.	Domain-specific logic	operations/
RestInvocation	REST API client	client/

🚀 Getting Started
Prerequisites
Java 8 or higher

Spring Boot 2.7.5

Installation
Clone the repository

bash
git clone https://github.com/enrique/xml-gateway.git
cd xml-gateway
Build the project

bash
mvn clean package
Run the application

bash
java -jar target/xml-gateway.jar
The service will start on http://localhost:8080 with SOAP endpoint available at http://localhost:8080/ws.

📡 SOAP Service
WSDL Location
WSDL: http://localhost:8080/ws/xmlservice.wsdl

Namespace: http://example.com/xmlservice

Request Example

<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope 
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:tns="http://example.com/xmlservice">

    <soapenv:Header/>

    <soapenv:Body>
        <tns:doAction >
            <tns:user>Enrique</tns:user>
            <tns:type>account</tns:type>
            <tns:action>CREATE</tns:action>
            <tns:name>details</tns:name>

            <tns:inputParams>
                <tns:param>
                    <tns:name>id</tns:name>
                    <tns:value>98765</tns:value>
                </tns:param>
                <tns:param>
                    <tns:name>name</tns:name>
                    <tns:value>Enrique Escalante</tns:value>
                </tns:param>
                <tns:param>
                    <tns:name>phoneNumber</tns:name>
                    <tns:value>667665668</tns:value>
                </tns:param>
                <tns:param>
                    <tns:name>address</tns:name>
                    <tns:value>marbella</tns:value>
                </tns:param>
            </tns:inputParams>
        </tns:doAction>
    </soapenv:Body>
</soapenv:Envelope>



Response Example
xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:doActionResponse xmlns:ns2="http://example.com/xmlservice">
            <ns2:code>0</ns2:code>
            <ns2:result>Configuration OK</ns2:result>
            <ns2:date>2026-02-01+01:00</ns2:date>
            <ns2:time>00:26:12.758+01:00</ns2:time>
        </ns2:doActionResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>


# SOAP Service
soap:
  namespace: http://example.com/xmlservice
  endpoint: /xmlservice
  
Service Registry
Services are automatically registered in the ServiceRouter:

Service Type	Implementation Class	Description
account	AccountService	Account-related operations
card	CardService	Card management operations
payment	PaymentService	Payment processing

🔧 Development
Project Structure
text
src/main/java/com/enrique/xmlgateway/
├── controller/
│   └── GatewayEndpoint.java          # SOAP endpoint
├── service/
│   ├── MainProcessorService.java     # Main orchestrator
│   └── ServiceRouter.java           # Service dispatcher
├── operations/
│   ├── AccountService.java          # Account operations
│   ├── CardService.java             # Card operations
│   └── PaymentService.java          # Payment operations
├── client/
│   └── RestInvocation.java          # REST client
├── model/
│   ├── RequestDTO.java              # Internal request
│   ├── ResponseDTO.java             # Internal response
└── config/
    └── WebClientConfig.java         # HTTP client config
    
Adding a New Service
Create service implementation

java
@Service("newservice")
@RequiredArgsConstructor
public class NewService implements TypeService {
    
    private final RestInvocation restInvocation;
    
    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws JsonProcessingException {
        // Business logic here
        return new ResponseDTO("200", "Operation completed");
    }
}


The service will be automatically registered in ServiceRouter via Spring's dependency injection.

Field Name Conversion
Services can define field name mappings for external APIs:

java
private static final Map<String, String> nameConversionMap = Map.ofEntries(
    entry("internalField", "externalField"),
    entry("phoneNumber", "phone")
);


SOAP Request Testing
Use tools like:

SoapUI

Postman (with SOAP plugin)


🔍 Monitoring & Logging
Health Check
Actuator Health: http://localhost:8080/actuator/health

Service Status: http://localhost:8080/actuator/info


🚨 Error Handling (TODO)
The service handles:

SOAP parsing errors → Returns SOAP fault

Service not found → Returns error code 1

REST API failures → Returns error code 99

JSON processing errors → Returns error code 99

🔄 Request Flow
Receive SOAP request at /ws

Parse and validate XML request

Route to appropriate service based on request type

Transform field names if conversion map exists

Call external REST API with transformed parameters

Process response and map to SOAP format

Return SOAP response with legacy status codes

📈 Performance Considerations
Connection pooling: WebClient uses Reactor Netty connection pooling

Timeout configuration: Externalize timeout settings for different APIs

Caching: Consider caching frequent requests

Async operations: Current implementation is synchronous; consider async for high throughput

🔒 Security
Current Implementation
No authentication/authorization (to be implemented)

HTTP only (consider HTTPS for production)

Recommended Additions
SOAP Security Headers (WS-Security)

API Key authentication for external APIs

Request signing

Rate limiting

🗺️ Roadmap
Implement proper status code translation

Add request/response logging

Implement circuit breaker pattern

Implement async processing

Implement caching layer



Maintained by Enrique | GitHub Repository

Last Updated: January 2024
