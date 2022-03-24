package utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "configs//Configuration.properties";


    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public static String encryptText(String plain) {
        int code;
        String result = "";
        for (int i = 0; i < plain.length(); i++) {
            code = Math.round((float) Math.random()*8+1);
            result += code + Integer.toHexString( ((int) plain.charAt(i) ) ^ code )+"-";
        }
        return result.substring(0, result.lastIndexOf("-"));
    }

    public static String decryptText(String text) {
        text = text.replace("-", "");
        String result = "";
        for (int i = 0; i < text.length(); i+=3) {
            String hex =  text.substring(i+1, i+3);
            result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(text.charAt(i)))));
        }
        return result;
    }

}