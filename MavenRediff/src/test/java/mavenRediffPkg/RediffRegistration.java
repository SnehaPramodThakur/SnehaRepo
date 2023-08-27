package mavenRediffPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RediffRegistration {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub

		WebDriverManager.firefoxdriver().setup();
		WebDriver d=new FirefoxDriver();
		
		d.get("http://register.rediff.com/register/register.php?FormName=user_details");
		d.manage().window().maximize();
		
		File file=new File("C:\\SnehaSel\\MavenRediff\\src\\test\\resources\\RediffRegistration.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		
		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(1).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount :"+colcount);
		for(int i=1;i<=colcount;i++)
		{
			XSSFRow celldata=sheet.getRow(i);
			
			String Name=celldata.getCell(0).getStringCellValue();
			String Email=celldata.getCell(1).getStringCellValue();
			String Password=celldata.getCell(2).getStringCellValue();
			String RetypePassword=celldata.getCell(3).getStringCellValue();
			String Mobilenumber=celldata.getCell(4).getStringCellValue();
			String DOBday=celldata.getCell(5).getStringCellValue();


			
			WebElement stbn1=d.findElement(By.xpath("//*[@id=\'tblcrtac\']/tbody/tr[3]/td[3]/input"));
			stbn1.sendKeys(Name);
			
			WebElement stbn2=d.findElement(By.xpath("//*[@id=\'tblcrtac\']/tbody/tr[7]/td[3]/input[1]"));
			stbn2.sendKeys(Email);

			WebElement stbn3=d.findElement(By.xpath("//*[@id=\'newpasswd\']"));
			stbn3.sendKeys(Password);
			
			WebElement stbn4=d.findElement(By.xpath("//*[@id=\'newpasswd1\']"));
			stbn4.sendKeys(RetypePassword);
			
			WebElement stbn5=d.findElement(By.xpath("//*[@id=\'mobno\']"));
			stbn5.sendKeys(Mobilenumber);
			
			WebElement dropd=d.findElement(By.xpath("/html/body/center/form/div/table[2]/tbody/tr[22]/td[3]/select[1]"));
	        dropd.click();
			Select ddn=new Select(dropd);
	        ddn.selectByVisibleText(DOBday);
			
		}
	}

}
