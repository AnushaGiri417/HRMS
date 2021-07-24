    package utils;

    import io.github.bonigarcia.wdm.WebDriverManager;
    import org.apache.commons.io.FileUtils;
    import org.openqa.selenium.*;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.io.File;
    import java.io.IOException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.concurrent.TimeUnit;

    public class CommonMethods {

        static String path;
        public static WebDriver driver;

        public static void setUp() {
            path = Constants.CONFIGURATION_FILEPATH;
            ConfigReader.readProperties( path );
            switch (ConfigReader.getPropertyValue( "browser" )) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                //    driver = new ChromeDriver();
                //    break;
                    if(ConfigReader.getPropertyValue( "headless" ).equals( "true" )){
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setHeadless( true );
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    }else{
                        driver=new ChromeDriver();
                        break;
                    }

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException( "Invalid name of browser" );
            }
            driver.get( ConfigReader.getPropertyValue( "url" ) );
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait( Constants.IMPLICIT_WAIT, TimeUnit.SECONDS );
        }

        public static void sendText(WebElement element, String text) {
            //   element.clear();
            element.sendKeys( text );
        }

        public static void click(WebElement element) {
            waitTillClickable( element );
            element.click();
        }

        public static WebDriverWait getWait() {
            WebDriverWait wait = new WebDriverWait( driver, Constants.EXPLICITE_WAIT );
            return wait;
        }

        public static void waitTillClickable(WebElement element) {
            getWait().until( ExpectedConditions.elementToBeClickable( element ) );
        }


        public static JavascriptExecutor getJSExecutor() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js;
        }
        public static void jsClick(WebElement element) {
            getJSExecutor().executeScript( "argument[0].click()", element );
        }

        public static byte[] takeScreenshot(String fileName) {
            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] picBytes = ts.getScreenshotAs( OutputType.BYTES );

            File sourceFile = ts.getScreenshotAs( OutputType.FILE );
            try {
                FileUtils.copyFile( sourceFile, new File( Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp( "yyyy-MM-dd-HH-mm-ss" ) + ".png" ) );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return picBytes;
        }

        public static String getTimeStamp(String pattern) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat( pattern );
            return sdf.format( date );
        }



        public static void teardown() {
            if (driver != null) {
                driver.quit();
            }
        }

    }
