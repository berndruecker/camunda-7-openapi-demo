/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.berndruecker.demo.plainjava;

import java.io.*;
import java.util.Collections;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DeploymentApi;
import org.openapitools.client.api.ProcessDefinitionApi;
import org.openapitools.client.model.StartProcessInstanceDto;
import org.openapitools.client.model.VariableValueDto;

public class App {

  private static String bpmnXmlPath = "bpmn/one_task.bpmn";
  private static String processId = "benchmark";

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
}