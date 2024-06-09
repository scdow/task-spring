package com.ccda.task.dao;
//data access object

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findByName(String name);

    List<Task> findByDeleted(boolean deleted);

    @Query("SELECT t from Task t WHERE t.deleted=false AND t.name LIKE %:name% AND t.code LIKE %:code% AND t.create_time BETWEEN :startDate AND :endDate")
    List<Task> findByQuery(@Param("name") String name, @Param("code") String code, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT t from Task t WHERE t.deleted=false AND t.name LIKE %:name% AND t.code LIKE %:code%")
    List<Task> findByQueryWithoutDate(@Param("name") String name, @Param("code") String code);


}
