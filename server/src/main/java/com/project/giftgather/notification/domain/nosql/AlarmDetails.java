package com.project.giftgather.notification.domain.nosql;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "alarm_details")
@Getter
public class AlarmDetails {

    @Id
    private String id;

    private String userId;  // 외래 키처럼 참조 ID로 사용
    private String projectId;  // 외래 키처럼 참조 ID로 사용

    private String type;

    private String message;

    private Date timestamp;

    private boolean read;
}