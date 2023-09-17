package core.dsl.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.unitils.reflectionassert.ReflectionAssert;
//import org.unitils.reflectionassert.ReflectionAssert;

public class APIRequest {
    protected String endPoint;
    protected Response response;
    protected Class ResponseClass;
    protected RequestSpecification request;
    public APIRequest(URI uri) {
        RestAssured.baseURI = uri.get();
        request = RestAssured.given();
        request.contentType("application/json");
    }
    public APIRequest(URI uri, Class responseClass) {
        this(uri);
        ResponseClass = responseClass;
    }
    public APIRequest(URI uri,String endPoint, Class responseClass) {
        this(uri,responseClass);
        this.endPoint = endPoint;
    }
    public void addHeader(String Header, String value) {
        request.header(Header, value);
    }

    public <ResponseClass> ResponseClass deserialize()
    {
        return (ResponseClass) response.as(ResponseClass);
    }
    public int getStatusCode() {
        return response.getStatusCode();
    }
    public String getStatusCodeAsString() {
        return String.valueOf( getStatusCode());
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    public <ResponseClass> void shouldMatch(ResponseClass respBody)
    {
        ReflectionAssert.assertReflectionEquals(respBody, deserialize());
    }
}
