package nl.sealsay.gatekeeper;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import nl.sealsay.gatekeeper.wsdl.DownloadReportResponse;
import nl.sealsay.gatekeeper.wsdl.DownloadReportRequest;


@Endpoint
public class DownloadReportResponseEndpoint {

    private static final String NAMESPACE_URI = "https://poortwachter.notarisid.nl/srs/1.0";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DownloadReportRequest")
    @ResponsePayload
    public DownloadReportResponse handleDownloadReportRequest(@RequestPayload DownloadReportRequest request) {
        // Create the response
        DownloadReportResponse response = new DownloadReportResponse();
        response.setId("310b333f-946f-4d4d-846c-34ffe64e2922");

        DownloadReportResponse.ScanReport scanReport = new DownloadReportResponse.ScanReport();
        DownloadReportResponse.ScanReport.Document document = new Document();
        document.setType("PASSPORT");
        document.setDocumentNumber("XN5004481");
        document.setCountryOfIssue("Ierland");
        document.setDateOfIssue("2013-09-16");
        document.setDateOfExpiry("2023-09-15");
        document.setGivenName("Lauren");
        document.setSurname("Osullivan");
        document.setGender("FEMALE");
        document.setNationality("Ierse");
        document.setDateOfBirth("1988-05-04");
        document.setPlaceOfBirth("Baile Átha Cliath/Dublin");
        scanReport.setDocument(document);

        Attachments attachments = new Attachments();
        Attachment attachment1 = new Attachment();
        attachment1.setData("cid:44f01995-444e-4c87-8d5a-753876843383-1@poortwachter.notarisid.nl");
        attachment1.setType("PHOTO_WHOLE_DOCUMENT");
        attachments.getAttachment().add(attachment1);

        // Add other attachments similarly...

        scanReport.setAttachments(attachments);
        scanReport.setValidationStatus("NOT_PASSED");

        response.setScanReport(scanReport);

        return response;
    }
}
