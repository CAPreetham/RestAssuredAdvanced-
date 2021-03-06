package jacksondatalib;
import java.util.Iterator;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class jacksonpath {

@Test
public void CreatingNestedJsonObjectTest() throws JsonProcessingException
{
// Create an object to ObjectMapper //
ObjectMapper objectMapper = new ObjectMapper();

// Create a Node that maps to JSON Object structures in JSON content //
ObjectNode bookingDetails = objectMapper.createObjectNode();

// It is similar to map put method. put method is overloaded to accept different types of data
// String as field value
bookingDetails.put("firstname", "C A");
bookingDetails.put("lastname", "Preetham");
// integer as field value
bookingDetails.put("totalprice", 1000);
// boolean as field value
bookingDetails.put("depositpaid", true);
bookingDetails.put("additionalneeds", "Breakfast");
// Duplicate field name. Will override value
bookingDetails.put("additionalneeds", "Lunch");
// To print created json object
String createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
System.out.println("Created plain JSON Object is : \n"+ createdPlainJsonObject);

// Since requirement is to create a nested JSON Object
ObjectNode bookingDateDetails = objectMapper.createObjectNode();
bookingDateDetails.put("checkin", "2021-07-01");
bookingDateDetails.put("checkout", "2021-07-01");

// Since 2.4 , put(String fieldName, JsonNode value) is deprecated. So use either set(String fieldName, JsonNode value) or replace(String fieldName, JsonNode value)
bookingDetails.set("bookingdates", bookingDateDetails);

// To get the created json object as string. Use writerWithDefaultPrettyPrinter() for proper formatting
String createdNestedJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
System.out.println("Created nested JSON Object is : \n"+ createdNestedJsonObject);

// We can retrieve field value by passing field name. Since it is string, use asText().
String firstName = bookingDetails.get("firstname").asText();
System.out.println("First name is : "+firstName);

// We can use asText() as well but return type will be string
boolean depositpaid = bookingDetails.get("depositpaid").asBoolean();
System.out.println("deposit paid is : "+depositpaid);

// To retrieve value of nested ObjectNode
bookingDetails.get("bookingdates").get("checkin").asText();
System.out.println("Checkin date is : "+depositpaid);

// To remove a field
String removedFieldValue = bookingDetails.remove("firstname").asText();
System.out.println("Value of Removed field is " + removedFieldValue);
String removedJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
System.out.println("After removing field , JSON Object is : \n"+ removedJsonObject);

// To replace a field value, use put() method for non ObjectNode type and replace() or set() for ObjectNode
bookingDetails.put("firstname", "C A");
bookingDetails.put("firstname", "Preetham");
String updatedJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
System.out.println("After updating field , JSON Object is : \n"+ updatedJsonObject);

}

}