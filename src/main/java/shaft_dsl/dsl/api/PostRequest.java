package shaft_dsl.dsl.api;

import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class PostRequest extends APIRequest{


    public PostRequest(URI uri, String endPoint,Class ResponseClass) {
        super(uri, endPoint,ResponseClass);
         }
    public void addFormParameter(String parameter, String value) {
        request.formParams(parameter, value);
    }
    public void addFormParameter(String parameter, String[] value) {
        List<String> values =  Arrays.asList(value);
        request.formParams(parameter, values);
    }
    public void addbody(Object body) {
        request.body(body);
    }
    public Response send()
    {
        this.response = request.post(endPoint);
        return response;
    }

}
