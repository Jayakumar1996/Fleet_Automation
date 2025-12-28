
package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PdfUtilities {
	
	    // ✅ Enable PDF download WITHOUT creating new browser
	public static void enableChromeDownloads(WebDriver driver, String downloadDir) {

        Map<String, Object> params = new HashMap<>();
        params.put("behavior", "allow");
        params.put("downloadPath", downloadDir);

        ((ChromeDriver) driver)
                .executeCdpCommand("Page.setDownloadBehavior", params);
    }

	// Wait
	public static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// File check
	public static boolean isFileDownloaded(String filePath) {
		return new File(filePath).exists();
	}

	public static File getLatestDownloadedFile(String dirPath, String filePrefix) {

		File dir = new File(dirPath);
		File[] files = dir.listFiles();

		File latestFile = null;
		long lastModified = 0;

		for (File file : files) {
			if (file.getName().startsWith(filePrefix) && file.getName().endsWith(".pdf")) {

				if (file.lastModified() > lastModified) {
					lastModified = file.lastModified();
					latestFile = file;
				}
			}
		}
		return latestFile;
	}
	
	 // ✅ Get number of pages in PDF
    public static int getPDFPageCount(String pdfPath) {
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            int pages = document.getNumberOfPages();
            document.close();
            return pages;
        } catch (Exception e) {
            throw new RuntimeException("Unable to read PDF pages", e);
        }
    }

	public static File waitForPDFDownload(String dirPath, String filePrefix, int timeoutSec) {

		File dir = new File(dirPath);

		if (!dir.exists() || !dir.isDirectory()) {
			throw new RuntimeException("Download directory not found: " + dirPath);
		}

		long endTime = System.currentTimeMillis() + timeoutSec * 1000;

		while (System.currentTimeMillis() < endTime) {

			File[] files = dir.listFiles();
			if (files == null)
				continue;

			File latestFile = null;
			long lastModified = 0;

			for (File file : files) {
				if (file.getName().startsWith(filePrefix) && file.getName().endsWith(".pdf")
						&& !file.getName().contains(".crdownload")) {

					if (file.lastModified() > lastModified) {
						lastModified = file.lastModified();
						latestFile = file;
					}
				}
			}
			if (latestFile != null) {
				return latestFile;
			}
		}
		return null;
	}

	// PDF read
	public static String readPDF(String pdfPath) {
		String text = "";
		try {
			PDDocument document = PDDocument.load(new File(pdfPath));
			PDFTextStripper stripper = new PDFTextStripper();
			text = stripper.getText(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public static void deleteDownloadedPdfs(String dirPath, String filePrefix) {
	    File dir = new File(dirPath);
	    if (!dir.exists()) return;

	    File[] files = dir.listFiles();
	    if (files == null) return;

	    for (File file : files) {
	        if (file.getName().startsWith(filePrefix)
	                && file.getName().endsWith(".pdf")) {
	            file.delete();
	        }
	    }
	}

}
