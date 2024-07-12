package nl.sealsay.gatekeeper.endpoints;

import nl.sealsay.gatekeeper.wsdl.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RetrieveReportSummariesEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.org/MessageDefinitions";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RetrieveReportSummariesRequest")
    @ResponsePayload
    public RetrieveReportSummariesResponse retrieveReportSummaries(@RequestPayload RetrieveReportSummariesRequest request) {
        RetrieveReportSummariesResponse response = new RetrieveReportSummariesResponse();

        // Example data
        ReportSummaryType reportSummary = new ReportSummaryType();
        reportSummary.setReportID("12345");
        reportSummary.setReportDate("2023-07-10T00:00:00Z");
        reportSummary.setReportName("Sample Report");

        response.getReportSummary().add(reportSummary);

        return response;
    }
}
