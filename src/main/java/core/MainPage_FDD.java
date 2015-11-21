package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainPage_FDD {

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

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		Properties properties = new Properties();
		properties.load(new FileInputStream(
				"./src/main/resources/macys.properties"));
		String url = properties.getProperty("url");
		String search_field_id = properties.getProperty("search_field_id");
		String search_submit_id = properties.getProperty("search_submit_id");
		String sizes_xpath = properties.getProperty("sizes_xpath");

		String csvFile = "./src/main/resources/test.csv";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		String SplitBy = "!#";

		String text_case_id = null;
		String id = null;
		String expected_sise = null;
		String actual_size = null;

		WebDriver driver = new FirefoxDriver();
		// driver.get(url);
		driver.manage().window().maximize();

		try {
			line = br.readLine();
			String[] csv = line.split(SplitBy);
			text_case_id = csv[0];
			id = csv[1];
			expected_sise = csv[2];

			br.close();

			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.id(search_field_id)).sendKeys(id);
			driver.findElement(By.id(search_submit_id)).click();

			List<WebElement> sizes = driver.findElements(By.xpath(sizes_xpath));
			List<String> ls = new ArrayList<String>();
			for (WebElement size : sizes) {
				ls.add(size.getText());
			} // for (WebElement size : sizes) {

			actual_size = ls.toString().replace("[", "").replace("]", "");
			if (expected_sise.equals(actual_size)) {
				System.out.println("Test Case ID: \t\t" + text_case_id
						+ " - PASSED");
			} else {
				System.out.println("Test Case ID: \t\t" + text_case_id
						+ " - FAILED");
			} // if (expected_sise.equals(ls.toString().replace("[",
			System.out.println("ID : \t\t\t" + id);
			System.out.println("Size Actual: \t\t" + actual_size);
			System.out.println("Size Expected: \t\t" + expected_sise);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		} // try {
	} // public static void main(String[] args) {
} // public class MainPage_FDD {