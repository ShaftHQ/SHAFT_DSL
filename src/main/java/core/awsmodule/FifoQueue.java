package core.awsmodule;

import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class FifoQueue extends SQS{


    public FifoQueue(String queueName) {
        super(queueName);
    }

    public String sendMsg(String MsgContent, int delayInSeconds)
    {open();
        SendMessageResponse value= sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageGroupId("12")
                .messageBody(MsgContent)
                .delaySeconds(delayInSeconds)
                .build());
        close();
        return value.messageId();
    }

}
