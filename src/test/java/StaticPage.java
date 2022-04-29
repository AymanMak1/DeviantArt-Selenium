public class StaticPage extends DeviantArtPageBase {

    public StaticPage(WebDriver driver){
        super(driver);
    }

    public void loadedCorrectlyOrNot(String[] pages){
        for (int i = 0; i < pages.length; i++) {
            this.driver.navigate().to(pages[i]);
            if(this.driver.getCurrentUrl() != null){
                Assert.assertEquals(pages[i],this.driver.getCurrentUrl());
                System.out.println("Static page has opened up correctly.("+pages[i]+")");
            }
        }

    }

}
