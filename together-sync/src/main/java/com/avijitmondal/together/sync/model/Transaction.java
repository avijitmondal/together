package com.avijitmondal.together.sync.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Transaction {

    @Id
    String id;

    @Indexed
    boolean success;

    @Indexed
    String userId;

    @Indexed
    long created;

}
