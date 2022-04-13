[![Camunda Platform 7](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)

# Use Camunda OpenAPI via auto-generated Java client

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

Now the client can be used in this project easily (https://github.com/berndruecker/camunda-platform-openapi-demo/blob/master/src/main/java/io/berndruecker/demo/App.java):

```java
  public static void main(String[] args) throws ApiException {
    ApiClient client = new ApiClient();

    new DeploymentApi(client).createDeployment(
            null,
            null,
            true,
            true,
            "benchmark",
            null,
            new File(App.class.getClassLoader().getResource(bpmnXmlPath).getFile())
    );
    System.out.println("DEPLOYED");

            new ProcessDefinitionApi(client).startProcessInstanceByKey(
            processId,
            new StartProcessInstanceDto()
                    .variables(Collections.singletonMap("json", new VariableValueDto().value("test").type("string"))));

    System.out.println("STARTED");
  }
```

Advantages:

* No need to manually generate anything
* The Camunda version is clear - and the open API spec is pulled from a defined artifact

Disadvantages:

* Possible friction with Maven Plugins in general
