package core.dsl.api;


import io.restassured.response.Response;



public class PutRequest extends PostRequest {

    public PutRequest(URI uri, String endPoint, Class ResponseClass) {
        super(uri, endPoint, ResponseClass);
    }

    @Override
    public Response send() {
        this.response = this.request.put(this.endPoint);
        return this.response;
    }

}
