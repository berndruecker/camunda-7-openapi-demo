package io.berndruecker.demo.springboot;

import io.berndruecker.demo.springboot.starter.CamundaApi;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ProcessDefinitionApi;
import org.openapitools.client.model.StartProcessInstanceDto;
import org.openapitools.client.model.VariableValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;

@Component
public class DoSomeSpringStuff {

    private static String bpmnXmlPath = "bpmn/one_task.bpmn";
    private static String processId = "benchmark";

    @Autowired
    private CamundaApi camundaApi; // either inject this

    @Autowired private ProcessDefinitionApi processDefinitionApi; // or diorectly this

    @PostConstruct
    public void doSomething() throws ApiException {
        camundaApi.deploymentApi().createDeployment(
                null,
                null,
                true,
                true,
                "benchmark",
                null,
                new File(DoSomeSpringStuff.class.getClassLoader().getResource(bpmnXmlPath).getFile()));

        System.out.println("DEPLOYED");

        processDefinitionApi.startProcessInstanceByKey(
                processId,
                new StartProcessInstanceDto()
                        .variables(Collections.singletonMap("json", new VariableValueDto().value("test").type("string"))));

        System.out.println("STARTED");
    }
}
