package org.steps;

import hooks.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DownloadPage;

public class PDFSteps extends BaseClass{
	
	DownloadPage pdfPage = new DownloadPage(driver);

    @Given("user is on the PDF download page")
    public void user_is_on_pdf_download_page() {
        pdfPage.verifyOnDownloadPage();

    }

    @When("user downloads the PDF file")
    public void user_downloads_pdf() {
        pdfPage.clickOnPDF();
    }

    @Then("PDF file should be downloaded successfully")
    public void pdf_file_should_be_downloaded_successfully() {
        pdfPage.verifyPDFDownloaded();
    }
 
    @Then("PDF content should contain expected data")
    public void pdf_content_should_contain_expected_data() {

		String expectedText = "OBJECTIVE";
		pdfPage.verifyPDFContent(expectedText);
	    pdfPage.cleanupDownloadedPdf();

    }

}
