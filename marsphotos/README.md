A URL (Uniform Resource Locator) is a subset of a URI that specifies where a resource exists and the mechanism for
retrieving it.

For example:

The following URL gets a list of available real estate properties on Mars:

https://android-kotlin-fun-mars-server.appspot.com/realestate

The following URL gets a list of Mars photos:

https://android-kotlin-fun-mars-server.appspot.com/photos

These URLs refer to an identified resource, such as /realestate or /photos, that is obtainable via the Hypertext
Transfer Protocol (http:) from the network. You are using the /photos endpoint in this codelab. An endpoint is a URL
that allows you to access a web service running on a server.

Note: The familiar web URL is actually a type of URI. This course uses both URL and URI interchangeably.

### Web service request
Each web service request contains a URI and is transferred to the server using the same HTTP protocol that's used by web
browsers, like Chrome. HTTP requests contain an operation to tell the server what to do.

Common HTTP operations include:

- GET for retrieving server data.
- POST for creating new data on the server.
- PUT for updating existing data on the server.
- DELETE for deleting data from the server.

Your app makes an HTTP GET request to the server for the Mars photos information, and then the server returns a response
to your app, including the image URLs.