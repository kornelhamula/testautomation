package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config config = null;
    private String browser = "";
    private String chromeDriverLink;
    private int maximumWaitInSeconds = 10;
    private int pollingInMilliSeconds = 200;

    private Config(){
        Properties props = new Properties();
        try {
            FileInputStream istream = new FileInputStream("config.properties");
            props.load(istream);
            istream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.browser=props.getProperty("browser");
        this.maximumWaitInSeconds=Integer.parseInt(props.getProperty("maximumWaitInSeconds"));
        this.pollingInMilliSeconds=Integer.parseInt(props.getProperty("pollingInMilliSeconds"));
        this.chromeDriverLink=props.getProperty("webdriver.chrome.driver");
        System.setProperty("webdriver.chrome.driver", chromeDriverLink);
    }

    public String getBrowserType() {
        return browser;
    }

    public int getMaximumWaitInSeconds() {
        return this.maximumWaitInSeconds;
    }

    public int getPollingInMilliSeconds() {
        return this.pollingInMilliSeconds;
    }

    public static Config getInstance() {
        if (config==null) {
            config=new Config();
        }
        return config;
    }
}
