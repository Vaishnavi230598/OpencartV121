package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass1;
import utilities.DataProviders;

public class TC003_LoginDDT extends Baseclass1
{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="datadriver")  
	// getting data provider from different class
	
	public void verify_loginDDT(String email, String pwd, String exp)
	{

		logger.info("****** Starting TC_003_LoginDDT ******");

		try
		{

			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickmyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);

			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			/*
			 Data is valid  - login success - test pass - logout
			                 login failed  - test fail

			 Data is invalid - login success - test fail - logout
			                 login failed  - test pass
			*/

			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage == true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}

			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage == true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}

		logger.info("****** Finished TC_003_LoginDDT ******");
	}
}
