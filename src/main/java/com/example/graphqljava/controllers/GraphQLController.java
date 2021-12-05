package com.example.graphqljava.controllers;

import com.example.graphqljava.models.requests.GraphQLRequest;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/graphql")
public class GraphQLController {

    private final GraphQL graphQL;
    private final GraphQLSchema schema;
    private final SchemaPrinter schemaPrinter;

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String schema(){
        return schemaPrinter.print(schema);
    }

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
