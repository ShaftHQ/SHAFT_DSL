package core.awsmodule;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

public class AwsParameterStore {
    static Region region = Region.EU_CENTRAL_1;
    public static  String  getParameter(String paraName) {
        SsmClient ssmClient = SsmClient.builder().region(region).build();
        GetParameterResponse parameterResponse= null;
        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(paraName)
                    .build();
            parameterResponse = ssmClient.getParameter(parameterRequest);
         } catch (SsmException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        ssmClient.close();
        return  parameterResponse.parameter().value();
    }
}
