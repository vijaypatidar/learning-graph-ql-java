package com.example.graphqljava.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyGraphQLObjectTypes {
    private final GraphQLObjectType userType = new GraphQLObjectType.Builder()
            .name("User")
            .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
            .field(builder -> builder.name("name").type(Scalars.GraphQLString))
            .field(builder -> builder.name("email").type(Scalars.GraphQLString))
            .build();
    private final GraphQLObjectType commentType = new GraphQLObjectType.Builder()
            .name("Comment")
            .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
            .field(builder -> builder.name("dateTime").type(Scalars.GraphQLString))
            .build();
    private final GraphQLObjectType postType = new GraphQLObjectType.Builder()
            .name("Post")
            .field(builder -> builder.name("postId").type(Scalars.GraphQLString))
            .field(builder -> builder.name("comments").type(new GraphQLList(commentType)))
            .field(builder -> builder.name("title").type(Scalars.GraphQLString))
            .field(builder -> builder.name("description").type(Scalars.GraphQLString))
            .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
            .build();

}
