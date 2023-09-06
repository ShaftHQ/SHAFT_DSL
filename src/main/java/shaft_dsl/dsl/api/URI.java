package shaft_dsl.dsl.api;

public class URI {
    public URI(String endPoint) {
        this.uri = endPoint;
    }

    String uri;
    public String get()
        {
            return uri;
        }
}
