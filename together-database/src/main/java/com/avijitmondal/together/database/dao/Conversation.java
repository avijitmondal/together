/*****************************************************************************
 * FILE NAME   : Conversation.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.dao;

import com.avijitmondal.together.core.bean.ConversationType;
import com.avijitmondal.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijitmondal.together.core.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * @author avijit
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "CONVERSATION")
public class Conversation {

    /**
     * Unique id for Conversation
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "ID")
    private UUID id;

    /**
     * Conversation Title
     */
    @Column(name = "TITLE")
    @NotBlank(message = "error.title.notblank")
    private String title;

    /**
     * Conversation Creator ID
     */
    @Column(name = "CREATOR_ID")
    private UUID creatorId;

    /**
     * Conversation channel ID
     */
    @Column(name = "CHANNEL_ID")
    @NotBlank(message = "error.channelid.notblank")
    private String channelId;

    /**
     * Conversation type
     */
    @Column(name = "TYPE", columnDefinition = "enum('GROUP','SINGLE')", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    /**
     * DateTime when conversation created
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * DateTime when the conversation last updated
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    /**
     * List of participants for this conversation
     */
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private Set<Participant> participants;
}
