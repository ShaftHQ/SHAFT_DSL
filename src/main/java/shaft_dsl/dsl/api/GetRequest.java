package shaft_dsl.dsl.api;

import io.restassured.response.Response;

public  class GetRequest extends APIRequest {

    public GetRequest(URI uri, String endPoint,Class ResponseClass) {
        super(uri,endPoint,ResponseClass);
    }

    public void addQueryParameter(String parameter, String... value) {
        request.queryParam(parameter, value);
    }

    public Response send()
    {
       this.response = request.get(endPoint);
       return response;
    }

}
