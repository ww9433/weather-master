package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface StudentService {
    List<Map> getAllStud();

    Map<Integer, Map> findStudById(List<Integer> ids);
}
