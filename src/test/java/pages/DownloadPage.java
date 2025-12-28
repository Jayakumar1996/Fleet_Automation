package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utils.PdfUtilities;

public class DownloadPage {

	private WebDriver driver;

	// By Locators
	private By verifyDownloadPage = By.xpath("//h3[normalize-space(text())='File Downloader']");
	private By samplePdfLink = By.xpath("//a[normalize-space(text())='Abhishek_Yadav.pdf']");
	private String downloadDir = System.getProperty("user.home") + "\\Downloads";
	private String pdfPrefix = "Abhishek_Yadav";

	public DownloadPage(WebDriver driver) {
		this.driver = driver;
		PdfUtilities.enableChromeDownloads(driver, downloadDir);
	}

	public void verifyOnDownloadPage() {
		String expectedTitle = "The Internet";
		String actualTitle = driver.getTitle();
		if (!actualTitle.equals(expectedTitle)) {
			throw new IllegalStateException("This is not the Downloads page. Current page title: " + actualTitle);
		}

		driver.findElement(verifyDownloadPage).isDisplayed();

	}

	public void clickOnPDF() {
		driver.findElement(samplePdfLink).click();
		PdfUtilities.waitForSeconds(5);

	}

	// Assertions
	public void verifyPDFDownloaded() {
		File pdf = PdfUtilities.waitForPDFDownload(downloadDir, pdfPrefix, 20);
		Assert.assertNotNull(pdf, "PDF file was not downloaded");
	}

	public void verifyPDFContent(String expectedText) {
		// Wait and get the latest downloaded PDF
		File pdf = PdfUtilities.waitForPDFDownload(downloadDir, pdfPrefix, 20);
		Assert.assertNotNull(pdf, "PDF not found");

		// Read PDF content
		String text = PdfUtilities.readPDF(pdf.getAbsolutePath());

		// ✅ Print PDF content in console
		int pageCount = PdfUtilities.getPDFPageCount(pdf.getAbsolutePath());
		System.out.println("=== PDF PAGE COUNT ===");
		System.out.println(pageCount);
		System.out.println("=== PDF CONTENT START ===");
		System.out.println(text);
		System.out.println("=== PDF CONTENT END ===");

		// Assert expected text
		Assert.assertTrue(text.contains(expectedText), "Expected content not found in PDF");
	}

	public void cleanupDownloadedPdf() {
		PdfUtilities.deleteDownloadedPdfs(downloadDir, pdfPrefix);
		System.out.println("Downloaded PDF cleaned up successfully");
	}

}
