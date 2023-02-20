package com.rahulshetty.apis;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.payloads.objects.FinalPayload;
import org.payloads.objects.placeIdPojo;
import org.payloads.objects.PutPojo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.endpoints.apis.EndPoints.POST_METHOD_ENDPOINT;

public class positiveEndToEndTest {
    private static Logger logger = LogManager.getLogger(rahulShettyAPIs.class);
    // public static Request request;
    public static Response response;
    public String post_request_file_path;
    public String put_request_file_path;
    public String delete_request_file_path;
    public String placeId;
    public String baseURI;

    @BeforeMethod
    public void setup() throws IOException {
        try (InputStream input = new FileInputStream("TestData/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            post_request_file_path = prop.getProperty("post_request_file_path");
            put_request_file_path = prop.getProperty("put_request_file_path");
            delete_request_file_path = prop.getProperty("delete_request_file_path");
            baseURI = prop.getProperty("base_url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = given();
        ObjectMapper object = new ObjectMapper();
        FinalPayload payload = object.readValue
                (new File(post_request_file_path), FinalPayload.class);
        RequestSpecification request = RestAssured.given();
        response = request.body(payload).post(POST_METHOD_ENDPOINT);
        String placeId = response.jsonPath().getString("place_id");
        this.placeId = placeId;
    }

    @Test(priority = 0)
    public void addNewPlaceIntoServer() throws IOException {
        ObjectMapper object = new ObjectMapper();
        FinalPayload payload = object.readValue
                (new File(post_request_file_path), FinalPayload.class);
        RequestSpecification request = RestAssured.given();
        System.out.println("request body is " + payload.toString());
        response = request.body(payload).post(POST_METHOD_ENDPOINT);
        System.out.println("response body is  " + response.asString());
        logger.info("new server added succesfully");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,
                "Correct status code returned");
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK"
                , "Correct status line returned");
        String contentType = response.getContentType();
        Assert.assertEquals(contentType, "application/json;charset=UTF-8"
        );
        String scope = response.jsonPath().getString("scope");
        Assert.assertEquals(scope, "APP");

        String status = response.jsonPath().getString("status");
        Assert.assertEquals(status, "OK");
        logger.info("new server added succesfully");

    }
    @Test(priority =1)
    void getExistingPlaceDetailsFromServer() throws IOException {
        RequestSpecification httpRequest = given();
        String final_url = "/maps/api/place/get/json?place_id="+placeId+"&key=qaclick123";
        Response response = httpRequest.request(Method.GET, final_url);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);
        logger.info("got existing place details from the server");
    }
    @Test(priority =2)
    public void updateExistingPlaceInServerWithNewValues() throws IOException {
        ObjectMapper object = new ObjectMapper();
        String final_url = "/maps/api/place/get/json?place_id="+placeId+"&key=qaclick123";
        PutPojo putrespobj = object.readValue
                (new File(put_request_file_path), PutPojo.class);
        putrespobj.setPlace_Id(placeId);
        RequestSpecification request = RestAssured.given();
        response = request.body(putrespobj).put(final_url);
        System.out.println("response body is  " + response.asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,
                "Correct status code returned");
        String statusLine = response.getStatusLine();
         logger.info("Updated place In server with new values");

    }
    @Test(priority =9)
    public void deleteTheServer() throws IOException {

        ObjectMapper object=new ObjectMapper();
        placeIdPojo readingDataPojo= object.readValue
                (new File(delete_request_file_path), placeIdPojo.class);
        readingDataPojo.setPlace_id(placeId);
        RequestSpecification request = RestAssured.given();
        response = request.body(readingDataPojo).post(POST_METHOD_ENDPOINT);
        System.out.println("response body is  " + response.asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200,
                "Correct status code returned");
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK"
                , "Correct status code returned");

        String status  = response.jsonPath().getString("status");
        Assert.assertEquals(status , "OK");
        logger.info("The server successfully deleted");


    }

}

