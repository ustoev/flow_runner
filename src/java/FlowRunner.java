import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


/**
 * Created by Sergei_Kovalkov on 3/13/2017.
 */
public class FlowRunner {

    // Here we will specify values for filling forms on pages
    // You can add it in next way:
    // private static String key = value;

    private static String URL_FIRST_PAGE_DEFAULT = "https://datalex3.jetblue.com/B6/CallCenterPortalStart.do";
    //private static String URL_FIRST_PAGE_DEFAULT = "https://getaways.datalex.jetblue.com/B6/CallCenterPortalStart.do";
    private static String USERNAME_DEFAULT = "ccapagent";
    private static String PASSWORD_DEFAULT = "ccapagent";
    private static String ORIGINAL_LOCATION_DEFAULT = "JFK";
    private static String DESTINATION_LOCATION_DEFAULT = "LAS";
    private static String DEPARTURE_DATE_DEFAULT = "07/14/2017";
    private static String ARRIVE_DATE_DEFAULT = "07/16/2017";
    private static String CABIN_TYPE_DEFAULT = "Economy";
    private static String ADULT_NUMBER_OF_PASSNGER_DEFAULT = "1";
    private static String KIDS_NUMBER_OF_PASSNGER_DEFAULT = "0";
    private static String INFANT_NUMBER_OF_PASSNGER_DEFAULT = "0";
    private static String UMNR_NUMBER_OF_PASSNGER_DEFAULT = "0";

    //private static String FORWARD_ROUTE_FARE_ID_DEFAULT = "flightSelectGr_0_5";
    //private static String BACKWARD_ROUTE_FARE_ID_DEFAULT = "flightSelectGr_1_62";

    private static String TITLE_DEFAULT = "Mr";
    private static String FIRST_NAME_DEFAULT = "John";
    private static String SECOND_NAME_DEFAULT = "Smith";
    private static String DAY_DEFAULT = "6";
    private static String MONTH_DEFAULT = "Jun";
    private static String YEAR_DEFAULT = "1983";
    private static String ADDRESS_LINE1_DEFAULT = "Star Avenue";
    private static String CITY_DEFAULT = "Folcroft";
    private static String COUNTRY_DEFAULT = "United States Of America";
    private static String STATE_DEFAULT = "Pennsylvania";
    private static String ZIPCODE_DEFAULT = "19032";
    private static String EMAIL_DEFAULT = "john.smith@test.com";
    private static String PRIMARY_PHONE_DEFAULT = "48562319";

    private static String CARD_TYPE_DEFAULT = "Visa";
    private static String CARD_NUMBER_DEFAULT = "4444333322221111";
    private static String CARD_VALID_MONTH_DEFAULT = "Jun";
    private static String CARD_VALID_YEAR_DEFAULT = "2021";
    private static String CARD_SECURITY_CODE_DEFAULT = "123";
    private static String CARD_CARDHOLDER_NAME_DEFAULT = "John Smith";
    private static String CARD_CARDHOLDER_PHONE_DEFAULT = "12345687";


    private static String SPIDER_NUMBER_DEFAULT = "123";
    private static String SPIDER_NAME_DEFAULT = "CCAPAgent";


    private  WebDriver driver = null;
    private WebDriverWait wait = null;

    @Test
    public void test() throws InterruptedException {

        Boolean elementExists = false;
       //driver.manage().window().maximize();

        init();
        // 1. set URL to specified application, for CCAP or other.
        // You can find it on first page on .28 or .58 or others
        driver.get(URL_FIRST_PAGE_DEFAULT);

        // Next steps will be uncommented for CCAP but you can close it with comment sign and
        // uncomment necessary




        /*FILL CREDENTIAL ON THE FIRST PAGE*/

        fillCredentialsPage();



        /*FILL ROUTE AND DATE FOR SEARCH*/
        searchFlowPage();


        /*SELECT FIRST FOR FORWARD AND FIRST FOR BACKWARD DIRECTION*/
        selectFlowResultPage();

        /*FILL PERSONAL DATA FORM*/

        fillTtravalerInfoPage();



        /*SELECT SEAT FROM SEATMAP*/
        selectSeatPage();

        /*SELECT ANCILARIES*/
        selectExtrasPage();

        /*FILL PAYMENT INFO*/
        fillPaymentPage();

    }


    /*
    Initialization of current FlowRunner instance's properties
    */
    public void init(){
        // Here we specify path to specific driver for your browser
        System.setProperty("webdriver.chrome.driver","C:\\dev\\webdriver\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();

        // Here we describe how long we will wait until any event will be finished
        // You can specify any time in seconds
        wait = new WebDriverWait(driver, 60);
    }

    private void fillCredentialsPage(){
        // 2. fill with account/password
        // This command send text to particular field. You only need to open page in debug mode (often F12 key),
        // select element on page with cursor and get Xpath from context menu
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(USERNAME_DEFAULT);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD_DEFAULT);
        driver.findElement(By.xpath("//*[@id=\"InternalAgentLoginForm\"]/div/table/tbody/tr[6]/td[2]/div/table/tbody/tr/td/a/span/span")).click();


    }

    private void searchFlowPage(){
        // Wait until element will be visible. Need to use the same way to get Xpath
        // Here we specify ORIGINAL LOC
        WebElement originalLoc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"outboundOption.originLocationName\"]")));
        originalLoc.sendKeys(ORIGINAL_LOCATION_DEFAULT);
        // Wait until in dropdown will occurs link with text "New York-Kennedy, US JFK"
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@id=\"drilldownItem1\"]"),"New York-Kennedy, US JFK"));
        // Click on specified
        driver.findElement(By.xpath("//*[@id=\"drilldownItem1\"]")).click();

        // Here we specify DESTINATION LOC
        driver.findElement(By.xpath("//*[@id=\"outboundOption.destinationLocationName\"]")).sendKeys(DESTINATION_LOCATION_DEFAULT);
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@id=\"drilldownItem1\"]"),"Las Vegas, US LAS"));
        driver.findElement(By.xpath("//*[@id=\"drilldownItem1\"]")).click();


        // Here we specify DATE OF DEPARTURE
        driver.findElement(By.xpath("//*[@id=\"departureDate1\"]")).sendKeys(DEPARTURE_DATE_DEFAULT);

        // Here we specify DATE OF ARRIVAL
        driver.findElement(By.xpath("//*[@id=\"departureDate2\"]")).sendKeys(ARRIVE_DATE_DEFAULT);

        Select cabinSelector = new Select(driver.findElement(By.id("cabinClass")));
        cabinSelector.selectByVisibleText(CABIN_TYPE_DEFAULT);

        Select adultSelector = new Select(driver.findElement(By.id("guestTypes[0].amount")));
        adultSelector.selectByVisibleText(ADULT_NUMBER_OF_PASSNGER_DEFAULT);

        Select kidsSelector = new Select(driver.findElement(By.id("guestTypes[1].amount")));
        kidsSelector.selectByVisibleText(KIDS_NUMBER_OF_PASSNGER_DEFAULT);

        Select infantSelector = new Select(driver.findElement(By.id("guestTypes[2].amount")));
        infantSelector.selectByVisibleText(INFANT_NUMBER_OF_PASSNGER_DEFAULT);

        Select umnrSelector = new Select(driver.findElement(By.id("guestTypes[3].amount")));
        umnrSelector.selectByVisibleText(UMNR_NUMBER_OF_PASSNGER_DEFAULT);

        // Here we click on Search button
        driver.findElement(By.xpath("//*[@id=\"AirFlightSearchForm\"]/div[15]/table[1]/tbody/tr/td/a/span/span")).click();
    }

    private void selectFlowResultPage(){
        // Here we select first route
        By firstForwarRouteXPath = By.xpath("(//*[@id='resultsFFBlock1']//td[contains(@class,'colCost_AN')][1]//input)[1]");
        //WebElement firstFare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FORWARD_ROUTE_FARE_ID_DEFAULT)));
        WebElement firstFare = wait.until(ExpectedConditions.visibilityOfElementLocated(firstForwarRouteXPath));
        firstFare.click();

        // Here we select second route
        // One bug exists in Chrome WebDriver so we will use JavaScript code for selecting

        By firstBackwardRouteXPath = By.xpath("(//*[@id='resultsFFBlock2']//td[contains(@class,'colCost_AN')][1]//input)[1]");
        //WebElement secondRoute = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BACKWARD_ROUTE_FARE_ID_DEFAULT)));
        WebElement secondRoute = wait.until(ExpectedConditions.visibilityOfElementLocated(firstBackwardRouteXPath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", secondRoute);

        // Here we wait until button CONTINUE will be available
        WebElement continueButton  = wait.until(ExpectedConditions.elementToBeClickable(By.id("pgButtonNext")));
        continueButton.click();

        driver.findElement(By.xpath("//*[@id=\"popupBaggageTermsBundleDialog\"]/div[4]/table/tbody/tr/td[2]/a")).click();
    }

    private void fillTtravalerInfoPage(){
        //driver.findElement(By.id()).sendKeys();
        //driver.findElement(By.xpath()).sendKeys();

        Select titleSelector = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("travellersInfo[0].title"))));
        titleSelector.selectByVisibleText(TITLE_DEFAULT);

        driver.findElement(By.id("travellersInfo[0].firstName")).sendKeys(FIRST_NAME_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].lastName")).sendKeys(SECOND_NAME_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].genderMale")).click();

        Select daySelector = new Select(driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(dobDay)")));
        daySelector.selectByVisibleText(DAY_DEFAULT);
        Select monthSelector = new Select(driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(dobMonth)")));
        monthSelector.selectByVisibleText(MONTH_DEFAULT);
        Select yearSelector = new Select(driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(dobYear)")));
        yearSelector.selectByVisibleText(YEAR_DEFAULT);

        driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(addressLine1)")).sendKeys(ADDRESS_LINE1_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(cityName)")).sendKeys(CITY_DEFAULT);
        Select countrySelector = new Select(driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(countryCode)")));
        countrySelector.selectByVisibleText(COUNTRY_DEFAULT);
        Select stateSelector = new Select(driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(stateCode)Display")));
        stateSelector.selectByVisibleText(STATE_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(postalCode)")).sendKeys(ZIPCODE_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].advancedPassengerDetails(personalEmail)")).sendKeys(EMAIL_DEFAULT);
        driver.findElement(By.id("travellersInfo[0].homePhone.phoneNumber")).sendKeys(PRIMARY_PHONE_DEFAULT);

        // Here we wait until button CONTINUE will be available
        WebElement continueButton2  = wait.until(ExpectedConditions.elementToBeClickable(By.id("pgButtonProceed")));
        continueButton2.click();
    }

    private void selectSeatPage(){
        // Here i skiped this page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mapmainDeck")));
        WebElement skipSeatSelection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("SkipSeatSelectLink")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();return true;", skipSeatSelection);
        //skipSeatSelection.click();

    }

    private void selectExtrasPage(){
        // Here i skiped this page
        WebElement skipAncilarySelection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"pgBookingPrepayment\"]/div[2]/div/a")));
        skipAncilarySelection.click();
    }

    private void fillPaymentPage(){


        WebElement selectCreditCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("formOfPayment(CREDITCARD_POS).selected")));
        selectCreditCard.click();

        // Fill credit card info
        WebElement cardTypeSelectorPath = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("creditCard.type")));
        Select cardTypeSelector = new Select(cardTypeSelectorPath);
        cardTypeSelector.selectByVisibleText(CARD_TYPE_DEFAULT);

        driver.findElement(By.id("creditCard.numberDisplay")).sendKeys(CARD_NUMBER_DEFAULT);

        Select cardExpMonthSelector = new Select(driver.findElement(By.id("creditCard.expirationMonth")));
        cardExpMonthSelector.selectByVisibleText(CARD_VALID_MONTH_DEFAULT);

        // Fill credit card info
        Select cardExpYearSelector = new Select(driver.findElement(By.id("creditCard.expirationYear")));
        cardExpYearSelector.selectByVisibleText(CARD_VALID_YEAR_DEFAULT);

        driver.findElement(By.id("creditCard.securityCodeDisplay")).sendKeys(CARD_SECURITY_CODE_DEFAULT);
        driver.findElement(By.id("creditCard.cardHolderName")).sendKeys(CARD_CARDHOLDER_NAME_DEFAULT);
        driver.findElement(By.id("creditCard.cardHolderPhone.phoneNumber")).sendKeys(CARD_CARDHOLDER_PHONE_DEFAULT);


        // Select Billing Address the same
        driver.findElement(By.id("billingAddressSameAsMailing")).click();


        // Open popup for adding SpiderNumber
        driver.findElement(
                By.xpath("/html/body/table/tbody/tr[1]/td/div[2]/div/div/div/div[5]/div[1]/a")).click();

        WebElement spiderNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("callerPhone")));
        spiderNumberElement.sendKeys(SPIDER_NUMBER_DEFAULT);
        driver.findElement(By.id("callerName")).sendKeys(SPIDER_NAME_DEFAULT);
        driver.findElement(By.xpath("//*[@id=\"popupModuleCallerInfoDialog\"]/div[4]/table/tbody/tr/td[3]/a")).click();

        // Here we wait until button CONTINUE will be available
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("spiderNumber"), SPIDER_NUMBER_DEFAULT));
        WebElement continueButton3  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pgButtonPurchase")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueButton3);
        //continueButton3.click();
    }
}
