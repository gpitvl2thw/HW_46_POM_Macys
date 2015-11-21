package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainPage_LF {

	public static List<String> expected_sizes(List<String> ls) {
		List<String> new_sizes = new ArrayList<String>();
		List<String> sizes = ls;
		if (sizes.contains("Twin")) {
			new_sizes.add("Twin");
		}
		if (sizes.contains("Twin XL")) {
			new_sizes.add("Twin XL");
		}
		if (sizes.contains("Full")) {
			new_sizes.add("Full");
		}
		if (sizes.contains("Full/Queen")) {
			new_sizes.add("Full/Queen");
		}
		if (sizes.contains("Queen")) {
			new_sizes.add("Queen");
		}
		if (sizes.contains("King")) {
			new_sizes.add("King");
		}
		if (sizes.contains("California King")) {
			new_sizes.add("California King");
		}
		List<String> expected_sizes = new_sizes;
		return expected_sizes;
	} // public static List<String> expected_sizes(List<String> ls) {

	public static void main(String[] args) {

		String text_case_id = "TC-001.01";
		String url = "http://www.macys.com";

		String id = "1762403";
		String expected_sise = "Full, Queen, King, California King";
		WebDriver driver = new FirefoxDriver();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("globalSearchInputField")).sendKeys(id);
		driver.findElement(By.id("subnavSearchSubmit")).click();

		List<WebElement> sizes = driver.findElements(By
				.xpath("//div[1]/div/div[1]/div[1]/div/div[1]/ul/li"));
		List<String> ls = new ArrayList<String>();
		for (WebElement size : sizes) {
			ls.add(size.getText());
		} // for (WebElement size : sizes) {

		driver.close();

		if (expected_sise.equals(ls.toString().replace("[", "")
				.replace("]", ""))) {
			System.out.println("Test Case ID: \t\t" + text_case_id
					+ " - PASSED");
			System.out.println("ID : \t\t\t" + id);
			System.out.println("Size Actual: \t\t"
					+ ls.toString().replace("[", "").replace("]", ""));
			System.out.println("Size Expected: \t\t" + expected_sise);
		} else {
			System.out.println("Test Case ID: \t\t" + text_case_id
					+ " - FAILED");
			System.out.println("ID : \t\t\t" + id);
			System.out.println("Size Actual: \t\t"
					+ ls.toString().replace("[", "").replace("]", ""));
			System.out.println("Size Expected: \t\t" + expected_sise);
		} // i if (expected_sise.equals(ls.toString().replace("[",
	} // public static void main(String[] args) {
} // public class MainPage_LF {