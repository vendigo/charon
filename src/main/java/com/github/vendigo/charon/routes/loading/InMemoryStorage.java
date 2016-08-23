package com.github.vendigo.charon.routes.loading;

import org.springframework.stereotype.Component;

import java.util.*;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@Component
public class InMemoryStorage {
    private final Map<String, List<Object>> storage = new HashMap<>();

    public void addObject(String sectionName, Object o) {
        notEmpty(sectionName, "section name must be not empty");
        notNull(o, "added object must be not null");
        List<Object> section;

        if (storage.containsKey(sectionName)) {
            section = storage.get(sectionName);
        } else {
            section = new ArrayList<>();
            storage.put(sectionName, section);
        }

        section.add(o);
    }

    public Map<String, List<Object>> getData() {
        return Collections.unmodifiableMap(storage);
    }
}
