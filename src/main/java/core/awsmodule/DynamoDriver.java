package core.awsmodule;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

import java.util.Map;

public class DynamoDriver {

    private AmazonDynamoDB client=AmazonDynamoDBClientBuilder.standard()
            .withRegion("eu-central-1")
                .build();
    private DynamoDB dynamoDB= new DynamoDB(client);

    public Item getItem(String tableName, GetItemSpec getItemSpec, String selectionConditionMsg) {
        Table table = dynamoDB.getTable(tableName);
        try {
            System.out.println("Attempting to read the item from "+ tableName + " with "+selectionConditionMsg);
            Item item = table.getItem(getItemSpec);
            System.out.println("... GetItem succeeded.");
            return item;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    public ItemCollection<QueryOutcome> getItems(String tableName, QuerySpec querySpec, String selectionConditionMsg) {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<QueryOutcome> items = table.query(querySpec);
        return items;
    }
    public  ItemCollection<ScanOutcome> scanItems(String tableName, String filterExpr, String projectionExpr, Map<String, Object> expressionAttributeValues) {
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan(filterExpr , // FilterExpression
                projectionExpr, // ProjectionExpression
                null, // ExpressionAttributeNames - not used in this example
                expressionAttributeValues);
        return items;
    }

    public void deleteItem(String tableName, DeleteItemSpec deleteItemSpec, String deletionConditionMsg) {
        Table table = dynamoDB.getTable(tableName);
        try {
            System.out.println("Attempting a conditional delete...");
            table.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
        } catch (Exception e) {
            System.err.println("Unable to delete item: " + deletionConditionMsg);
            System.err.println(e.getMessage());
        }
    }
public void batchWrite(TableWriteItems tableWriteItems)
{
    System.out.println(dynamoDB.batchWriteItem(tableWriteItems).getBatchWriteItemResult());
//    BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(tableWriteItems).getBatchWriteItemResult();
//    System.out.println(outcome);
}
}