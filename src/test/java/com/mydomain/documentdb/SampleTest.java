package com.mydomain.documentdb;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.RequestOptions;
import com.mydomain.model.Person;

public class SampleTest {

	// https://github.com/Azure/azure-documentdb-java
	// http://blogs.msdn.com/b/documentdb/archive/2014/08/22/introducing-azure-documentdb-microsoft-s-fully-managed-nosql-document-database-service.aspx?PageIndex=2
	// http://stackoverflow.com/questions/26654721/accessing-azure-documentdb-using-java
	// https://github.com/Azure/azure-content/blob/master/articles/documentdb-java-application.md
	// http://blogs.msdn.com/b/windowsazurej/archive/2015/01/08/introducing-the-azure-documentdb-java-sdk.aspx
	// http://azure.microsoft.com/blog/2015/01/06/introducing-the-azure-documentdb-java-sdk/

	// TodoList 管理のサンプルアプリが参考になる
	// http://azure.microsoft.com/en-us/documentation/articles/documentdb-java-application/
	
	// ドキュメント作成系
	// https://msdn.microsoft.com/ja-jp/library/azure/dn803933.aspx
	
	// プレビューの制限
	// http://azure.microsoft.com/ja-jp/documentation/articles/documentdb-limits/

	// Define an id for your database and collection
	private static final String DATABASE_ID = "TestDB";
	private static final String COLLECTION_ID = "TestCollection";

	// documentdb.properties から情報を取得
	private static String END_POINT;
	private static String MASTER_KEY;

	private static DocumentClient documentClient;
	private static Database databaseCache;
	private static DocumentCollection collectionCache;

	@Before
	public void testInitialize() throws IOException {
		String path = "documentdb.properties";
		InputStream in = SampleTest.class.getClassLoader().getResourceAsStream(
				path);
		if (in == null) {
			throw new IllegalArgumentException(path + " not found.");
		}
		Properties props = new Properties();
		props.load(in);

		END_POINT = props.getProperty("END_POINT");
		MASTER_KEY = props.getProperty("MASTER_KEY");
		documentClient = new DocumentClient(END_POINT, MASTER_KEY,
				ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
	}

	@Test
	public void readTest01() throws DocumentClientException,
			JsonParseException, JsonMappingException, IOException {
		Person expected = new Person();
		Person actual;

		expected.setAge(45);
		expected.setName("割と普通");

		List<Document> documentList = documentClient
				.queryDocuments(getTestCollection().getSelfLink(),
						"SELECT * FROM root r WHERE r.id='" + 1 + "'", null)
				.getQueryIterable().toList();
		ObjectMapper mapper = new ObjectMapper();
		actual = mapper.readValue(documentList.get(0).toString(), Person.class);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void createTest01() throws JsonGenerationException,
			JsonMappingException, IOException, DocumentClientException {
		Person expected = new Person();
		expected.setName("とても普通");
		expected.setAge(37);

		boolean disableAutomaticIdGeneration = false;
		Document document = new Document(
				new ObjectMapper().writeValueAsString(expected));
		Document response = documentClient.createDocument(//
				getTestCollection().getSelfLink(),//
				document, //
				null, //
				disableAutomaticIdGeneration).getResource();
		Person actual = new ObjectMapper().readValue(response.toString(),
				Person.class);
		Assert.assertEquals(expected, actual);
	}

	private Database getTestDBDatabase() {
		if (databaseCache == null) {
			// Get the database if it exists
			List<Database> databaseList = documentClient
					.queryDatabases(
							"SELECT * FROM root r WHERE r.id='" + DATABASE_ID
									+ "'", null).getQueryIterable().toList();

			if (databaseList.size() > 0) {
				// Cache the database object so we won't have to query for it
				// later to retrieve the selfLink.
				databaseCache = databaseList.get(0);
			} else {
				// Create the database if it doesn't exist.
				try {
					Database databaseDefinition = new Database();
					databaseDefinition.setId(DATABASE_ID);

					databaseCache = documentClient.createDatabase(
							databaseDefinition, null).getResource();
				} catch (DocumentClientException e) {
					// TODO:
					e.printStackTrace();
				}
			}
		}

		return databaseCache;
	}

	private DocumentCollection getTestCollection() {
		if (collectionCache == null) {
			// Get the collection if it exists.
			List<DocumentCollection> collectionList = documentClient
					.queryCollections(
							getTestDBDatabase().getSelfLink(),
							"SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
									+ "'", null).getQueryIterable().toList();

			if (collectionList.size() > 0) {
				// Cache the collection object so we won't have to query for it
				// later to retrieve the selfLink.
				collectionCache = collectionList.get(0);
			} else {
				// Create the collection if it doesn't exist.
				try {
					DocumentCollection collectionDefinition = new DocumentCollection();
					collectionDefinition.setId(COLLECTION_ID);

					// Configure the new collection performance tier to S1.
					RequestOptions requestOptions = new RequestOptions();
					requestOptions.setOfferType("S1");

					collectionCache = documentClient.createCollection(
							getTestDBDatabase().getSelfLink(),
							collectionDefinition, requestOptions).getResource();
				} catch (DocumentClientException e) {
					// TODO:
					e.printStackTrace();
				}
			}
		}

		return collectionCache;
	}
}
