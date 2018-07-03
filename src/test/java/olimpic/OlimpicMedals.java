package olimpic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlimpicMedals {

	public class OlympicsGames {

		WebDriver driver;
		String actualRank = "";

		@BeforeClass // runs once for all tests
		public void setUp() {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().fullscreen();
		}

		@BeforeMethod
		public void frontPage() {
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		}

		@Test(priority = 1)
		public void sortedByRank() {
			List<WebElement> rank = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr/td[1]"));
			String actualRank = "";
			String expectedRank = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ";
			for (int i = 0; i < rank.size() - 1; i++) {
				actualRank += rank.get(i).getText() + ", ";
			}
			Assert.assertEquals(actualRank, expectedRank, "Rank is not in ascending order");
			driver.findElement(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//th[2]")).click();

		}

		@Test(priority = 3)
		public void checkCountries() {
			List<WebElement> countries = driver.findElements(By
					.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody/tr/th[1]"));
			List<String> listOfCountries = new ArrayList<String>();
			for (WebElement each : countries) {
				listOfCountries.add(each.getText());
				System.out.println(each.getText());
			}
			boolean sorted = true;
			for (int i = 1; i < listOfCountries.size(); i++) {
				if (listOfCountries.get(i).compareTo(listOfCountries.get(i - 1)) > 0) {
					continue;
				} else {
					sorted = false;
					break;
				}
			}
			Assert.assertTrue(sorted, "Not sorted");
		}

		@Test(priority = 4)
		public void checkRanks() {
			List<WebElement> rank = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr/td[1]"));
			String actualRank = "";
			String expectedRank = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ";

			for (int i = 0; i < rank.size() - 1; i++) {
				actualRank += rank.get(i).getText() + ", ";
				System.out.println("----------");
				System.out.println(rank.get(i).getText() + ", ");
			}

			Assert.assertFalse(expectedRank.equals(actualRank));
		}

		@Test(priority = 5)
		public void mostGoldMedal() {
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			Map<Integer, String> data = new HashMap<Integer, String>();
			List<WebElement> listOfgold = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//td[2]"));
			List<WebElement> listOfCountry = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//a"));
			for (int i = 0; i < 10; i++) {
				data.put(Integer.parseInt(listOfgold.get(i).getText()), listOfCountry.get(i).getText());
			}
			SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet());
			// System.out.println(keys.last());
			System.out.println(data.get(keys.last()));
			System.out.println(data);
			System.out.println(data.size());
			String expected = "United States";
			Assert.assertEquals(data.get(keys.last()), expected);
		}

		@Test(priority = 6)
		public void mostSilver() {
			Map<Integer, String> data = new HashMap<Integer, String>();
			List<WebElement> listOfsilver = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//td[3]"));
			List<WebElement> listOfCountry = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//a"));
			for (int i = 0; i < 10; i++) {
				data.put(Integer.parseInt(listOfsilver.get(i).getText()), listOfCountry.get(i).getText());
			}

			SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet());
			// System.out.println(keys.last());
			System.out.println(data.get(keys.last()));
			System.out.println(data);

			String expected = "United States";
			System.out.println(data.size());
			Assert.assertEquals(data.get(keys.last()), expected);

		}

		@Test(priority = 7)
		public void mostBronzMedal() {

			Map<Integer, String> data = new HashMap<Integer, String>();
			List<WebElement> listOfbronz = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//td[4]"));
			List<WebElement> listOfCountry = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//a"));
			for (int i = 0; i < 10; i++) {
				data.put(Integer.parseInt(listOfbronz.get(i).getText()), listOfCountry.get(i).getText());
			}

			SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet());
			// System.out.println(keys.last());
			System.out.println(data.get(keys.last()));
			System.out.println(data);
			String expected = "United States";
			System.out.println(data.size());
			Assert.assertEquals(data.get(keys.last()), expected);

		}

		@Test(priority = 8)
		public void mostNumberOfMedal() {

			Map<Integer, String> data = new HashMap<Integer, String>();
			List<WebElement> listOfnumber = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//td[5]"));
			List<WebElement> listOfCountry = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//a"));
			for (int i = 0; i < 10; i++) {
				data.put(Integer.parseInt(listOfnumber.get(i).getText()), listOfCountry.get(i).getText());
			}

			SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet());
			// System.out.println(keys.last());
			System.out.println(data.get(keys.last()));
			System.out.println(data);
			String expected = "United States";
			System.out.println(data.size());
			Assert.assertEquals(data.get(keys.last()), expected);

		}
	}

	// @AfterClass
	// public void close() throws InterruptedException {
	// Thread.sleep(2000);
	// driver.close();
	// }
}
