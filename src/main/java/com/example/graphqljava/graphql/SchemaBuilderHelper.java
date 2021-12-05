package com.example.graphqljava.graphql;

import graphql.Scalars;
import graphql.schema.FieldCoordinates;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SchemaBuilderHelper {
    private final MyGraphQLObjectTypes myGraphQLObjectTypes;
    private final MyDataFetchers myDataFetchers;

    public void buildQuery(GraphQLObjectType.Builder queryBuilder, GraphQLCodeRegistry.Builder codeRegistry) {
        queryBuilder.name("Query");
        buildUserQuery(queryBuilder, codeRegistry);
        buildUsersQuery(queryBuilder,codeRegistry);
    }

    private void buildUserQuery(GraphQLObjectType.Builder queryBuilder, GraphQLCodeRegistry.Builder codeRegistry) {
        //getting GraphQLObjectType for User
        GraphQLObjectType userType = myGraphQLObjectTypes.getUserType();
        //creating field user of type User
        queryBuilder.field(builder -> builder
                .name("user")
                .type(userType)
                .argument(builder1 -> builder1.name("email")
                        .type(Scalars.GraphQLString)));

        //adding dataFetcher for user field which is defined in Query type
        codeRegistry.dataFetcher(FieldCoordinates.coordinates("Query", "user"), myDataFetchers.getUserDataFetcher());
    }

    private void buildUsersQuery(GraphQLObjectType.Builder queryBuilder, GraphQLCodeRegistry.Builder codeRegistry) {
        //getting GraphQLObjectType for User
        GraphQLObjectType userType = myGraphQLObjectTypes.getUserType();
        //creating field users of type List(User)
        queryBuilder.field(builder -> builder
                .name("users")
                .type(new GraphQLList(userType)));

        //adding dataFetcher for user field which is defined in Query type
        codeRegistry.dataFetcher(FieldCoordinates.coordinates("Query", "users"), myDataFetchers.getUsersDataFetcher());
    }


}
