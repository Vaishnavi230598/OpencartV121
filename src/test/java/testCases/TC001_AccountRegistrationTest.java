package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.Baseclass1;

public class TC001_AccountRegistrationTest extends Baseclass1 {
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*** Starting TC001_AccountRegistrationTest");
	
		try {
			
		
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		hp.clickRegister();
		AccountRegistrationPage regpage =  new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		String password = randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		String confmsg = regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
		    Assert.assertTrue(true);
		}
		else
		{
		    logger.error("Test failed..");
		    logger.debug("Debug logs..");
		    Assert.assertTrue(false);
		}

		//Assert.assertEquals(confmsg,"Your Account Has Been Created!");

		}
		catch(Exception e)
		{
		    Assert.fail();
		}

		logger.info("***** Finished TC001_AccountRegistrationTest *****");
}
}
