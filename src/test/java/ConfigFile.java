import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


class ConfigFile{

    public static Properties readUserCredentials(){     
        try{
            Properties props = new Properties();
            InputStream input =  new FileInputStream("resources//config.properties");
            props.load(input);
            System.out.println(props.getProperty("username"));
            return props;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
}