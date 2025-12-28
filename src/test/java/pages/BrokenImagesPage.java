package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrokenImagesPage {

    WebDriver driver;

    public BrokenImagesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Using By locator
    private By brokenImagesLink = By.xpath("//a[normalize-space(text())='Broken Images']");
    private By brokenImage = By.xpath("//h3[text()='Broken Images']");

    public void clickBrokenImagesSection() {
        driver.findElement(brokenImagesLink).click();
    }

    public String getBrokenImagesPageTitle() {
        return driver.getTitle();
    }
    
	public boolean isBrokenImageDisplayed() {
		WebElement imageElement = driver.findElement(brokenImage);
		return imageElement.isDisplayed();
	}


}
