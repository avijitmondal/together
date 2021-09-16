/*****************************************************************************
 * FILE NAME   : Conversation.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.dto;

import com.avijitmondal.together.core.bean.ConversationType;
import com.avijitmondal.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijitmondal.together.core.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
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
public class Conversation {

    /**
     * Unique id for Conversation
     */
    private UUID id;

    /**
     * Conversation Title
     */
    @NotBlank(message = "error.title.notblank")
    private String title;

    /**
     * Conversation Creator ID
     */
    private UUID creatorId;

    /**
     * Conversation channel ID
     */
    @NotBlank(message = "error.channelid.notblank")
    private String channelId;

    /**
     * Conversation type
     */
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    /**
     * DateTime when conversation created
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    /**
     * DateTime when the conversation last updated
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    /**
     * List of participants for this conversation
     */
    private Set<Participant> participants;
}
