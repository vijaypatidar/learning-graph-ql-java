# Getting Started with GraphQl:Java

## 1. Add maven dependency
```xml
   <dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphql-java</artifactId>
    <version>16.2</version>
   </dependency>
```
## 2. Create model class for graphQl request 

```java
@Data
public class GraphQLRequest {
    private String query;
    private String operationName;
    private Map<String, Object> variables;
}
```

## 3. Now we will add a post endpoint for handling GraphQL request
```java

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
```

