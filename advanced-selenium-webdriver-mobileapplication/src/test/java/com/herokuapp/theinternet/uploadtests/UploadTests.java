package com.herokuapp.theinternet.uploadtests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.FileUploaderPage;

public class UploadTests extends TestUtilities {

	@Test(dataProvider="files")// we are using dataprovider so mention dataprovider name
	public void fileUploadTest(int no, String fileName) {
		//We are passing integer and string from dataprovider method
		log.info("Starting fileUploadTest #" + no + "fileName");

		// open File Uploader Page
		FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
		fileUploaderPage.openPage();

		// Select file
		//Create folder file under src/main/resources and add file logo.png or any other same name provide here
		//String fileName = "logo.png";(we r passing file from data provider so no need of it)
		fileUploaderPage.selectFile(fileName);

		// Push upload button
		fileUploaderPage.pushUploadButton();

		// Get uploaded files
		String fileNames = fileUploaderPage.getUploadedFilesNames();

		// Verify selected file uploaded
		Assert.assertTrue(fileNames.contains(fileName),
				"Our file (" + fileName + ") is not one of the uploaded (" + fileNames + ")");
	}
}
