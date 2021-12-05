package com.example.graphqljava.graphql;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyDataFetchers {

    public DataFetcher<List<Map<String,Object>>> getUsersDataFetcher(){
        return env->{
            Map<String,Object> user1 = new HashMap<>();
            user1.put("name","vijay");
            user1.put("email","vijay@example.com");

            Map<String,Object> user2 = new HashMap<>();
            user2.put("name","Ram");
            user2.put("email","Ram@example.com");

            return List.of(user1,user2);
        };
    }

    public DataFetcher<Map<String,Object>> getUserDataFetcher(){
        return env->{
            String emailArg = env.getArgument("email");
            Map<String,Object> user2 = new HashMap<>();
            user2.put("name","vijay");
            user2.put("email",emailArg);
            return user2;
        };
    }
}
