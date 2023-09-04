package shaft_dsl.dsl.api;


import io.restassured.response.Response;

public class DeleteRequest extends APIRequest {
    public DeleteRequest(URI uri) {
        super(uri);
    }

    public Response send() {
        this.response = (Response)this.request.delete(this.endPoint);
        return this.response;
    }
}
