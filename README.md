# camunda-platform-openapi-demo

Small demo, that creates a Java client for the Camunda REST API via the provided OpenAPI file on the fly using a Maven Plugin: 

```
      <plugin>
        <groupId>org.openapitools</groupId>
        <artifactId>openapi-generator-maven-plugin</artifactId>
        <executions>
          <execution>
            <goals>
              <goal>generate</goal>
            </goals>
            <configuration>
              <inputSpec>/openapi.json</inputSpec>
              <generatorName>java</generatorName>
              <configOptions>
                <dateLibrary>legacy</dateLibrary>
                <sourceFolder>src/gen/java/main</sourceFolder>
              </configOptions>
            </configuration>
          </execution>
        </executions>
        <dependencies>
          <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-engine-rest-openapi</artifactId>
            <version>${version.camunda}</version>
          </dependency>
        </dependencies>
      </plugin>
```

Now the client can be used in this project easily. 

Advantages:

* No need to manually generate anything
* The Camunda version is clear - and the open API spec is pulled from a defined artifact

Disadvantages:

* Possible friction with Maven Plugins in general
