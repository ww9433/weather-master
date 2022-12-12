package com.example.search.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class StudentServiceImpl implements StudentService {

    private final ExecutorService pool;
    private final RestTemplate ribbonRestTemplate;

    public StudentServiceImpl(ExecutorService pool, RestTemplate ribbonRestTemplate) {
        this.pool = pool;
        this.ribbonRestTemplate = ribbonRestTemplate;
    }

    @Override
    public List<Map> getAllStud() {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        completableFutureList.add(
                CompletableFuture.supplyAsync(
                        () -> ribbonRestTemplate.getForObject("http://university-service/university/student?all", Map.class),
                        pool
                )
        );

        return CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
                .thenApply(
                        VOID -> {
                            List<Map> res = new ArrayList<>();
                            for (int i = 0; i < completableFutureList.size(); i++) {
                                res.add((Map) completableFutureList.get(i).join());
                                System.out.println(res);
                            }
                            return res;
                        }).join();
    }

    @Override
    public Map<Integer, Map> findStudById(List<Integer> ids) {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        for(int id: ids) {
            completableFutureList.add(
                    CompletableFuture.supplyAsync(
                            () -> ribbonRestTemplate.getForObject("http://university-service/university/student?id=" + id, HashMap.class)
                            , pool
                    )
            );
        }
        return CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
                .thenApply(VOID -> {
                    Map<Integer, Map> res = new HashMap<>();
                    for(int i = 0; i < completableFutureList.size(); i++) {
                        res.put(ids.get(i), (Map) completableFutureList.get(i).join());
                    }
                    return res;
                }).join();
    }
}
