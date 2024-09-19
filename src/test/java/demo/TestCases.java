package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

public class TestCases extends ExcelDataProvider{ 
        ChromeDriver driver;

        Wrappers wrappers;

        @Test
        public void testCase01() {
                

                driver.get("https://www.youtube.com/");
                SoftAssert softAssert = new SoftAssert();

                String currentURL = driver.getCurrentUrl();
                softAssert.assertTrue(currentURL.contains("youtube"), "URL does not contain 'youtube'");

                wrappers.clickElement(By.id("guide-button"));

                wrappers.clickElement(By.linkText("About"));

                wrappers.waitForVisibility(By.className("ytabout__content"));

                String aboutMessage = wrappers.getText(By.className("ytabout__content"));
                System.out.println("About message: " + aboutMessage);

                driver.navigate().back();

                softAssert.assertAll();
        }


        @Test
        public void testCase02() {

                driver.get("https://www.youtube.com/");                

                SoftAssert softAssert = new SoftAssert();   

                wrappers.waitForVisibility(By.id("guide-button"));                

                wrappers.clickElement(By.id("guide-button"));

                wrappers.clickElement(By.xpath("//a[@title=\"Movies\"]")); 

                wrappers.waitForVisibility(By.xpath("(//button[@aria-label=\"Next\"])[1]"));

                boolean buttonVisible = wrappers.findElement(By.xpath("(//button[@aria-label=\"Next\"])[1]")).isDisplayed();

                while (buttonVisible) {

                        try {
                                wrappers.findElement(By.xpath("(//button[@aria-label=\"Next\"])[1]")).click();   
                        } catch (Exception e) {
                                break;
                        }

                }

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='primary']/ytd-section-list-renderer/div[@id='contents']/ytd-item-section-renderer[1]/div[@id='contents']/ytd-shelf-renderer/div[@id='dismissible']/div[@id='contents']/yt-horizontal-list-renderer/div[@id='scroll-outer-container']/div/div/ytd-grid-movie-renderer[last()]/ytd-badge-supported-renderer/div[2]")));

                String AgeRating = wrappers.findElement(By.xpath("//div[@id='primary']/ytd-section-list-renderer/div[@id='contents']/ytd-item-section-renderer[1]/div[@id='contents']/ytd-shelf-renderer/div[@id='dismissible']/div[@id='contents']/yt-horizontal-list-renderer/div[@id='scroll-outer-container']/div/div/ytd-grid-movie-renderer[last()]/ytd-badge-supported-renderer/div[2]")).getAttribute("aria-label");


                softAssert.assertNotEquals(AgeRating.contains("A"), "The movie is for Mature people.");

                String category = wrappers.findElement(By.xpath("//div[@id='primary']/ytd-section-list-renderer/div[@id='contents']/ytd-item-section-renderer[1]/div[@id='contents']/ytd-shelf-renderer/div[@id='dismissible']/div[@id='contents']/yt-horizontal-list-renderer/div[@id='scroll-outer-container']/div/div/ytd-grid-movie-renderer[last()]/a/span")).getText();

                boolean categoryExists = category.contains("Comedy") || category.contains("Animation") || category.contains("Drama");

                softAssert.assertTrue(categoryExists, "The movie category is not 'Comedy', 'Animation', or 'Drama'");

                softAssert.assertAll();

        }


        @Test
        public void testCase03() throws InterruptedException {

                driver.get("https://www.youtube.com/");
                SoftAssert softAssert = new SoftAssert();

                wrappers.clickElement(By.id("guide-button"));

                wrappers.clickElement(By.xpath("//a[@title=\"Music\"]")); 

                wrappers.waitForVisibility(By.xpath("(//button[@aria-label='Next'])[1]"));

                boolean buttonVisible = wrappers.findElement(By.xpath("(//button[@aria-label='Next'])[1]")).isDisplayed();

                while (buttonVisible) {

                        try {
                                wrappers.findElement(By.xpath("(//button[@aria-label='Next'])[1]")).click();   
                        } catch (Exception e) {
                                break;
                        }

                }


                String playListTitle = wrappers.findElement(By.xpath("//*[@id='items']/ytd-compact-station-renderer[last()]/div/a/h3")).getText();

                System.out.println("PlayList Title: "+ playListTitle);

                String totalTracks = wrappers.findElement(By.xpath("//*[@id='items']/ytd-compact-station-renderer[last()]/div/a/p")).getText();

                String[] Tracks = totalTracks.split(" ");

                String TracksString = Tracks[0];

                int TracksInt =  Integer.parseInt(TracksString);

                boolean checkTracks = (TracksInt <= 50);

                softAssert.assertTrue(checkTracks, "tracks listed is Not less than or equal to 50.");

                softAssert.assertAll();

        }

        @Test
        public void testCase04() throws InterruptedException {
                driver.get("https://www.youtube.com/");
                SoftAssert softAssert = new SoftAssert();

                driver.findElement(By.id("guide-button")).click();

                driver.findElement(By.xpath("//a[@title='News']")).click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ytd-two-column-browse-results-renderer[@page-subtype='news']/div[@id='primary']/ytd-rich-grid-renderer/div[@id='contents']/ytd-rich-section-renderer[2]/div[@id='content']/ytd-rich-shelf-renderer/div[@id='dismissible']/div[@id='contents']/ytd-rich-item-renderer")));

                List<WebElement> newsItemList = driver.findElements(By.xpath("//ytd-two-column-browse-results-renderer[@page-subtype='news']/div[@id='primary']/ytd-rich-grid-renderer/div[@id='contents']/ytd-rich-section-renderer[2]/div[@id='content']/ytd-rich-shelf-renderer/div[@id='dismissible']/div[@id='contents']/ytd-rich-item-renderer"));

                System.out.println("Number of news items found: " + newsItemList.size());

                if (newsItemList.size() < 3) {
                        System.out.println("Less than 3 news items found.");
                        softAssert.fail("Less than 3 news items found.");
                        softAssert.assertAll();
                        return;
                }

                int[] newslikesArray = new int[3];

                for (int i = 0; i < 3; i++) {
                        WebElement itemList = newsItemList.get(i);

                        Thread.sleep(2000);

                        // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div")));

                        String newsHeader = itemList.findElement(By.xpath(".//div[@id='header']")).getText();
                        String newsBody = itemList.findElement(By.xpath(".//div[@id='body']")).getText();
                        String newsLiked = itemList.findElement(By.xpath(".//span[@id='vote-count-left']")).getAttribute("aria-label");

                        String[] likeTrim = new String[2];

                        if(newsLiked.contentEquals("")){
                                newsLiked = "0 like";
                        } else {
                                likeTrim = newsLiked.split(" ");
                                newslikesArray[i] = Integer.parseInt(likeTrim[0]);
                        }

                        System.out.println((i + 1) + ". News title: " + newsHeader + "\nNews details: " + newsBody + "\nNews Liked: " + likeTrim[0]+"\n");
                }

                int totalLikes = 0;
                for (int likes : newslikesArray) {
                        totalLikes += likes;
                }

                System.out.println("Total Likes on all 3 posts: " + totalLikes);

                softAssert.assertAll();
        }

        @Test(dataProvider = "excelData")
        public void testCase05(String searchTerm) {

                driver.get("https://www.youtube.com/");
                
                WebElement searchBox = driver.findElement(By.name("search_query"));
                searchBox.sendKeys(searchTerm);
                WebElement searchButton = driver.findElement(By.id("search-icon-legacy"));
                searchButton.click();

                SoftAssert softAssert = new SoftAssert();

                long totalViews = 0;
                int scrollCount = 0;

                while (totalViews < 10_000_000) {
                        scrollCount++;

                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        List<WebElement> videoList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ytd-video-renderer")));

                        for (WebElement video : videoList) {
                                String viewsText = video.findElement(By.xpath(".//div[@id='metadata-line']/span[1]")).getText();
                                totalViews += convertViewsToNumber(viewsText);
                                System.out.println("Accumulated views: " + totalViews);

                                if (totalViews >= 10_000_000) {
                                        break;
                                }
                        }

                        scrollToEndOfPage();

                        System.out.println("Scroll Count: " + scrollCount);
                }

                System.out.println("Total views for " + searchTerm + ": " + totalViews);

                softAssert.assertTrue(totalViews >= 10_000_000, "Failed to accumulate enough views.");
                softAssert.assertAll();
        }

        /**
         * Converts YouTube view strings (e.g., "1.2M", "305K") into numeric values.
         * 
         * @param viewsText The view string (e.g., "1.2M views")
         * @return The numeric value of views (e.g., 1200000), or 0 if the string is not valid.
         */
        private long convertViewsToNumber(String viewsText) {

                viewsText = viewsText.replace(" views", "").replace(",", "");
        
                if (viewsText.contains("watching") || viewsText.contains("now") || viewsText.isEmpty()) {
                        return 0; 
                }
        
                if (viewsText.endsWith("M")) {
                        return (long) (Double.parseDouble(viewsText.replace("M", "")) * 1_000_000);
                } else if (viewsText.endsWith("K")) {
                        return (long) (Double.parseDouble(viewsText.replace("K", "")) * 1_000);
                } else if (viewsText.endsWith("B")) {
                        return (long) (Double.parseDouble(viewsText.replace("B", "")) * 1_000_000_000);
                } else {
                        
                        try {
                                return (long) Double.parseDouble(viewsText);
                        } catch (NumberFormatException e) {
                                return 0;
                        }
                }
        }
        

        private void scrollToEndOfPage() {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }

        

        /*
        * Do not change the provided methods unless necessary, they will help in
        * automation and assessment
        */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("lang=en-US");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                wrappers = new Wrappers(driver);
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}