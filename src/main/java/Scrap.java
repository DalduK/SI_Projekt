import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.*;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.management.DynamicMBean;


public class Scrap {

    ArrayList<ArrayList<Integer>> nonX = new ArrayList<>();
    ArrayList<ArrayList<Integer>> nonY = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Integer>>> non = new ArrayList<>();

    public Scrap(String num){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://webpbn.com/export.cgi");
            driver.findElement(By.xpath("//input[@value='cwd']")).click();
            driver.findElement(By.name("id")).sendKeys(num + Keys.ENTER);
            String site = driver.getPageSource();
            BufferedReader bufReader = new BufferedReader(new StringReader(site));
            String line=null;
            int X = 0;
            int count = 0;
            while( (line=bufReader.readLine()) != null )
            {
                //TODO: zaktulizowac metode aby przyjmowala tez puste tabele nonogrmow
                if(count < 2){
                    count++;
                    continue;
                }
                ArrayList<Integer> temp= new ArrayList<>();
                line = line.replace("<html><head></head><body><pre style=\"word-wrap: break-word; white-space: pre-wrap;\">","");
                line = line.replace("</pre></body></html>","");
                if(line.equals("")){
                    X = 1;
                    continue;
                }
                String[] st = line.split(" ");
                for(int i = 0; i < st.length;i++){
                    temp.add(Integer.parseInt(st[i]));
                }
                if(X == 0){
                    nonX.add(temp);
                }else{
                    nonY.add(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            non.add(nonX);
            non.add(nonY);
            driver.quit();
        }
    }

    public Integer[][][] getNon() {
        System.out.println(non);
        Integer[][][] nonI = non.stream().map(u1 -> u1.stream().map(u2 -> u2.toArray(new Integer[0])).toArray(Integer[][]::new)).toArray(Integer[][][]::new);
        return nonI;
    }
}
