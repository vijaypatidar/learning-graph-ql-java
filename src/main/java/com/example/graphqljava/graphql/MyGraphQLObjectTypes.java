package com.example.graphqljava.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyGraphQLObjectTypes {
    private GraphQLObjectType userType = new GraphQLObjectType.Builder()
            .name("User")
            .field(builder ->
                    builder
                            .name("name")
                            .type(Scalars.GraphQLString)
            )
            .field(builder ->
                    builder
                            .name("email")
                            .type(Scalars.GraphQLString)
            ).build();

}
