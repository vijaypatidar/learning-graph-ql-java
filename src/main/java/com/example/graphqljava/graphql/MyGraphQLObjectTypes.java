package com.example.graphqljava.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class MyGraphQLObjectTypes {


    private final MyDataFetchers myDataFetchers;
    private GraphQLInputObjectType postInputType;
    private GraphQLObjectType userType;
    private GraphQLObjectType commentType;
    private GraphQLObjectType postType;

    public MyGraphQLObjectTypes(MyDataFetchers myDataFetchers) {
        this.myDataFetchers = myDataFetchers;
    }

    @PostConstruct
    private void init() {
        userType = new GraphQLObjectType.Builder()
                .name("User")
                .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
                .field(builder -> builder.name("name").type(Scalars.GraphQLString))
                .field(builder -> builder.name("email").type(Scalars.GraphQLString))
                .build();
        commentType = new GraphQLObjectType.Builder()
                .name("Comment")
                .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
                .field(builder -> builder.name("dateTime").type(Scalars.GraphQLString))
                .build();
        postType = new GraphQLObjectType.Builder()
                .name("Post")
                .field(builder -> builder.name("postId").type(Scalars.GraphQLString))
                .field(builder -> builder.name("comments").type(new GraphQLList(commentType)))
                .field(builder -> builder.name("title").type(Scalars.GraphQLString))
                .field(builder -> builder.name("description").type(Scalars.GraphQLString))
                .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
                .build();
        postInputType = new GraphQLInputObjectType.Builder()
                .name("PostInput")
                .field(builder -> builder.name("title").type(Scalars.GraphQLString))
                .field(builder -> builder.name("description").type(Scalars.GraphQLString))
                .field(builder -> builder.name("userId").type(Scalars.GraphQLString))
                .build();
    }

}
