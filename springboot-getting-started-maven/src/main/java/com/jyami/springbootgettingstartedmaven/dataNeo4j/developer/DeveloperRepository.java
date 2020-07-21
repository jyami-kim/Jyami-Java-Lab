package com.jyami.springbootgettingstartedmaven.dataNeo4j.developer;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Created by jyami on 2020/07/21
 */
public interface DeveloperRepository extends Neo4jRepository<Developer, Long> {
}
