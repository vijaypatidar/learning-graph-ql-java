package com.example.graphqljava.graphql;

import graphql.Scalars;
import graphql.schema.*;
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
        buildUsersQuery(queryBuilder, codeRegistry);
        buildNoteQuery(queryBuilder, codeRegistry);
        buildNotesQuery(queryBuilder, codeRegistry);
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

    private void buildNoteQuery(GraphQLObjectType.Builder queryBuilder, GraphQLCodeRegistry.Builder codeRegistry) {
        GraphQLObjectType noteType = myGraphQLObjectTypes.getPostType();
        queryBuilder.field(builder -> builder.name("post").type(noteType)
                .argument(builderArg -> builderArg.name("postId").type(new GraphQLNonNull(Scalars.GraphQLString))));
        codeRegistry.dataFetcher(FieldCoordinates.coordinates("Query", "post"), myDataFetchers.getPostDataFetcher());
    }

    private void buildNotesQuery(GraphQLObjectType.Builder queryBuilder, GraphQLCodeRegistry.Builder codeRegistry) {
        GraphQLObjectType noteType = myGraphQLObjectTypes.getPostType();
        queryBuilder.field(builder -> builder.name("posts").type(new GraphQLList(noteType)));
        codeRegistry.dataFetcher(FieldCoordinates.coordinates("Query", "posts"), myDataFetchers.getPostsDataFetcher());
    }

}
