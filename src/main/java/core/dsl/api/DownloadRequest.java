package core.dsl.api;

import com.shaft.validation.Validations;
import java.io.FileOutputStream;

public class DownloadRequest extends PostRequest{
    public DownloadRequest(URI uri, String endPoint, Class ResponseClass) {
        super(uri, endPoint, ResponseClass);
    }
    public String getDownloadFileName()
    {
     return response.getHeader("Content-Disposition").replace("attachment;filename=", "");
    }
    public void shouldHaveName(String name)
    {
        Validations.assertThat().object(getDownloadFileName()).contains(name).perform();
    }
    public int getDownloadFileSize()
    {
        byte[] bytes = response.getBody().asByteArray();
        System.out.println("file size is " + bytes.length);
        return  bytes.length;
    }
    public void download()
    {
        try {
            FileOutputStream zippedBill = new FileOutputStream(System.getProperty("testDataFolderPath") + getDownloadFileName().replace("\"",""));
            byte[] bytes = response.getBody().asByteArray();
            zippedBill.write(bytes);
            zippedBill.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error message"+ e.getMessage());
            Validations.assertThat().forceFail()
                    .withCustomReportMessage("downloaded response is not proper file ")
                    .perform();
        }
    }
    public void fileShouldContainText(String value )
    {
        Validations.assertThat().object(response.body().asString()).contains(value).perform();
    }
}
