package com.ccda.task.dao;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

//映射database的table的对象
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", columnDefinition = "VARCHAR(36)")
    private String code;

    @Column(name = "create_time")
    private Date create_time;

    @Column(name = "update_time")
    private Date update_time;

    @Column(name = "delete_time")
    private Date delete_time;

    @Column(name = "deleted")
    private boolean deleted;

    @PrePersist
    protected void onCreate() {
        create_time = new Date();
        update_time = new Date();
        code = UUID.randomUUID().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        update_time = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

//type_dict = {'int': 'Integer', 'varchar': 'String', 'char': 'String', 'nchar': 'String',\
//        'nvarchar': 'String', 'text': 'String', 'ntext': 'String', 'tinyint': 'Integer',\
//        'smallint': 'Integer', 'bit': 'Boolean', 'bigint': 'Long', 'float': 'Double',\
//        'decimal': 'BigDecimal', 'money': 'BigDecimal', 'smallmoney': 'BigDecimal',\
//        'numeric': 'BigDecimal', 'real': 'Float', 'uniqueidentifier': 'String',\
//        'smalldatetime': 'Timestamp', 'datetime': 'Date', 'timestamp': 'byte[]', 'binary': 'byte[]',\
//        'varbinary': 'byte[]', 'image': 'byte[]', 'sql_variant': 'String', 'double':'Double'}

//mysql tigger
//BEGIN
//        SET new.CODE = UUID();
//END
