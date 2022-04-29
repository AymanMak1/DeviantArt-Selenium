import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Properties;


public class ConfigFile(){

    static Properties prop = new Properties();

    public static void readUserCredentials(){
        try{
            InputStream input =  new FileInputStream("config.properties");
            prop.load(input);
            LoginPage.username = prop.getProperty("username");
            LoginPage.password = prop.getProperty("password");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}