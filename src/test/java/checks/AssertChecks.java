package checks;

import org.testng.Assert;
import response.EmptyResponseInDeletedFolder;
import response.GetUrlForUploadFileResponse;
import response.MakeFolderResponse;
import utils.Log;

import static io.restassured.RestAssured.given;

public class AssertChecks {
    /* Check Folder Creation*/
    public void checkFolderCreation(MakeFolderResponse actual, String url, String folderName) {

        String href = url + "?path=disk%3A%2F" + folderName.replaceAll("\\/", "%2F");
        Boolean result = false;
        String expectedMethod = "GET";

        Assert.assertEquals(actual.getHref(), href, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethod, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct");
    }

    public void checkDeletedFolder(Integer result){
        //System.out.println(" --------------- "+ result +" --------------- ");
        Integer expectedStatus = 204;
        Assert.assertEquals(result, expectedStatus, "Expected status is not 204");

        Log.info("The folder successfully deleted");
    }

    public void checkFolderForUploadFileCreation (GetUrlForUploadFileResponse actual, String url, String folderName){
        Boolean result = false;
        String expectedMethod = "PUT";

        //Assert.assertEquals(actual.getOperationId(), , "Expected templated is not equal actual");
        //Assert.assertEquals(actual.getHref(), href, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethod, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct");
    }
}
