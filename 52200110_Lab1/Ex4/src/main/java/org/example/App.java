package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws FileNotFoundException {
        if (args == null || args.length == 0){
            System.out.println("Please specify an URL to file");
            return;
        }

        String[] scheme = {"http", "https"};
        UrlValidator urlVal = new UrlValidator(scheme);

        if (!urlVal.isValid(args[0])){
            System.out.println("This is not a valid url");
        }else{
            try {
                InputStream inStream = new URL(args[0]).openStream();
                File fileDestination = new File(getUrl(args[0]));
                FileUtils.copyToFile( inStream,  fileDestination);
                System.out.println("Download successfully.\nPath -> " + fileDestination.getAbsolutePath());
            } catch (RuntimeException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public static String getUrl(String url){
        int lastSpl = url.lastIndexOf("/");
        return url.contains(".") ? url.substring(lastSpl+1)+".html" : url.substring(lastSpl+1);

    }
}
