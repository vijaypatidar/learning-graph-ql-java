package com.example.graphqljava.config;

import com.example.graphqljava.graphql.SchemaBuilderHelper;
import graphql.GraphQL;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public SchemaPrinter getSchemaPrinter() {
        return new SchemaPrinter();
    }

    @Bean
    public GraphQLSchema getGraphQLSchema(SchemaBuilderHelper schemaBuilderHelper) {
        GraphQLCodeRegistry.Builder codeRegistry = GraphQLCodeRegistry.newCodeRegistry();
        GraphQLObjectType.Builder queryBuilder = GraphQLObjectType.newObject();
        //build query type
        schemaBuilderHelper.buildQuery(queryBuilder,codeRegistry);

        return GraphQLSchema.newSchema()
                .query(queryBuilder.build())
                .codeRegistry(codeRegistry.build())
                .build();
    }

    @Bean
    public GraphQL getGraphQl(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
