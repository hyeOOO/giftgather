package com.project.giftgather.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectStoryRequest {
    private String title;
    private String representativeImage;
    private String type;
    private String file;
    private String description; //프로젝트 요약
    private boolean reviewApproval;
    private String story;
}
