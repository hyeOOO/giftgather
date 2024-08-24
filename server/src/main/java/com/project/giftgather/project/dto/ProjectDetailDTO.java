package com.project.giftgather.project.dto;

import com.project.giftgather.project.domain.nosql.ProjectDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailDTO {
    private String projectId;
    private String story;
    private IntroductionMediaDTO introductionMedia;
    private List<UpdateDTO> updates;
    private int wishes;
    private int backers;
    private List<ProjectDocumentDTO> documents;

    // IntroductionMediaDTO 클래스 정의
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IntroductionMediaDTO {
        private String type;
        private String url;
    }

    // UpdateDTO 클래스 정의
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDTO {
        private String title;
        private String content;
        private List<String> images;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    // ProjectDocumentDTO 클래스 정의
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectDocumentDTO {
        private String documentId; // MongoDB에서는 필요 없을 수도 있음
        private String documentName;
        private String documentUrl;
        private LocalDateTime createdAt; // Optional
        private LocalDateTime updatedAt; // Optional
    }

    public static ProjectDetailDTO convertToProjectDetailDTO(ProjectDetail projectDetail) {
        ProjectDetailDTO dto = new ProjectDetailDTO();

        dto.setProjectId(projectDetail.getProjectId());
        dto.setStory(projectDetail.getStory());
        dto.setWishes(projectDetail.getWishes());
        dto.setBackers(projectDetail.getBackers());

        if (projectDetail.getIntroductionMedia() != null) {
            dto.setIntroductionMedia(new ProjectDetailDTO.IntroductionMediaDTO(
                    projectDetail.getIntroductionMedia().getType(),
                    projectDetail.getIntroductionMedia().getUrl()
            ));
        }

        if (projectDetail.getUpdates() != null) {
            dto.setUpdates(projectDetail.getUpdates().stream()
                    .map(update -> new ProjectDetailDTO.UpdateDTO(
                            update.getTitle(),
                            update.getContent(),
                            update.getImages(),
                            update.getCreatedAt(),
                            update.getUpdatedAt()
                    ))
                    .collect(Collectors.toList()));
        }

        if (projectDetail.getDocuments() != null) {
            dto.setDocuments(projectDetail.getDocuments().stream()
                    .map(doc -> new ProjectDocumentDTO(
                            null, // documentId는 사용하지 않음
                            doc.getName(),
                            doc.getUrl(),
                            null, // createdAt은 null로 설정 (MongoDB에서 관리)
                            null  // updatedAt은 null로 설정 (MongoDB에서 관리)
                    ))
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}