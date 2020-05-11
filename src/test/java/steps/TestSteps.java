package steps;

import checks.AssertChecks;
import io.qameta.allure.Step;
import request.SendRequest;
import response.EmptyResponseInDeletedFolder;
import response.GetUrlForUploadFileResponse;
import response.MakeFolderResponse;
import utils.Log;

import java.io.File;

public class TestSteps {

    private final static String BASE_DISK_URL = "https://cloud-api.yandex.net/v1/disk/resources";
    //private final static String BASE_DISK_URL = "https://cloud-api.yandex.net/v1/disk/resources";
    private final static String TOKEN = "AgAAAABAWWRvAAZUGbXs552EVERij8KKQVHZiQY";

    private SendRequest sendRequest = new SendRequest();
    private AssertChecks checks = new AssertChecks();

    @Step("Create folder step")
    public MakeFolderResponse createFolderMethod(String folderName) {
        Log.info("PUT reguest for create folder");

        MakeFolderResponse makeFolderActualResponse;
        makeFolderActualResponse = sendRequest.putRequest(BASE_DISK_URL, TOKEN, MakeFolderResponse.class, folderName);

        checks.checkFolderCreation(makeFolderActualResponse, BASE_DISK_URL, folderName);
        return makeFolderActualResponse;
    }

    @Step("Delete folder step")
    public void deleteFolderMethod(String url) {
        Log.info("Delete reguest for folder delete");
        Integer status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");
                
        status = sendRequest.deleteRequest(href, TOKEN, "true");

        checks.checkDeletedFolder(status);
    }

    @Step("Create folder and get Url for uploading file step")
    public GetUrlForUploadFileResponse getUrlForFileFolderMethod(String folderForFile) {
        Log.info("Get request method for create folder and get Url for uploading file ");

        GetUrlForUploadFileResponse getUrlForUploadFileResponse;
        getUrlForUploadFileResponse = sendRequest.getRequest(BASE_DISK_URL+"/upload", TOKEN, GetUrlForUploadFileResponse.class, folderForFile);

        checks.checkFolderForUploadFileCreation(getUrlForUploadFileResponse, BASE_DISK_URL, folderForFile);

        return getUrlForUploadFileResponse;
    }

    public void uploadFile(String url, String method, String file, String fileName) {
        GetUrlForUploadFileResponse getUrlForUploadFileResponse;
        getUrlForUploadFileResponse = sendRequest.putRequestWithAttachments(url, TOKEN, GetUrlForUploadFileResponse.class, file, fileName);
    }
}
