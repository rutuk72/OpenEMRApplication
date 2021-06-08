package com.cyient.test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cyient.base.WebDriverWrapper;
import com.cyient.page.DashboardPage;
import com.cyient.page.LoginPage;
import com.cyient.page.PatientFinderPage;

public class PatientTest extends WebDriverWrapper {

	@Test
	public void addPatientTest() throws InterruptedException {
		
		LoginPage login=new LoginPage(driver);
		login.sendUsername("admin");
		login.sendPassword("pass");
		login.selectLanaguageByText("English (Indian)");
		login.clickOnLogin();

		//DashboardPage
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.mousehoverOnPatientClient();
		dashboard.clickOnPatients();
		
		//PatientFinderPage
		PatientFinderPage patientFinder=new PatientFinderPage(driver);
		patientFinder.switchToFinFrame();
		patientFinder.clickOnAddNewPatient();
		patientFinder.switchOutOfFrame();

		//SearchOrAddPatientPage
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
		driver.findElement(By.id("form_fname")).sendKeys("bala");
		driver.findElement(By.id("form_lname")).sendKeys("dina");
		driver.findElement(By.id("form_DOB")).sendKeys("2021-06-07");
		Select selectGender = new Select(driver.findElement(By.id("form_sex")));
		selectGender.selectByVisibleText("Male");
		driver.findElement(By.id("create")).click();
		driver.switchTo().defaultContent();

		Thread.sleep(5000);

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();

		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		String actualAlertText = driver.switchTo().alert().getText();
		System.out.println(actualAlertText);

		driver.switchTo().alert().accept();

		driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();

		//PatientDashboardPage
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
		String actualValue = driver.findElement(By.xpath("//h2[contains(text(),'Medical Record')]")).getText();
		System.out.println(actualValue);

		
		//no need to move to any page objects
		//assertion on alertbox and added patient detail
		Assert.assertTrue(actualAlertText.contains("New Due Clinical Reminders"));// must be true
		Assert.assertEquals(actualValue, "Medical Record Dashboard - Bala Dina");// must be Medical Record Dashboard - Bala Dina
																					
	}

}
