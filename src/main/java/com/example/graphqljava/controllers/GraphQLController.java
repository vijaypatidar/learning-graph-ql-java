package com.example.graphqljava.controllers;

import com.example.graphqljava.models.requests.GraphQLRequest;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/graphql")
public class GraphQLController {

    private final GraphQL graphQL;

    @PostMapping
    public Object execute(@RequestBody GraphQLRequest graphQLRequest) {
        Map<String, Object> variables = Optional.ofNullable(graphQLRequest.getVariables())
                .orElse(new HashMap<>());

        ExecutionInput input = new ExecutionInput
                .Builder()
                .query(graphQLRequest.getQuery())
                .operationName(graphQLRequest.getOperationName())
                .variables(variables).build();

        ExecutionResult execute = graphQL.execute(input);

        Map<String, Object> result = new HashMap<>();
        result.put("data", execute.getData());

        //set error if any
        if (!execute.getErrors().isEmpty()) {
            result.put("error", execute.getErrors());
        }

        return result;
    }
}
