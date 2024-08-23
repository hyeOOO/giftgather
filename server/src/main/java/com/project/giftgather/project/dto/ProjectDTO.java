package com.project.giftgather.project.dto;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.status.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String projectId;
    private int projectNumber;
    private String title;
    private String description;
    private BigDecimal goalAmount;
    private BigDecimal currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus projectStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String representativeImage;
    private boolean reviewApproval;

    private MakerDTO maker;
    private List<ProjectRewardDTO> projectRewards;
    private List<ProjectWishDTO> projectWishes;
    private List<SupporterDTO> supporters;
    private List<ProjectDocumentDTO> projectDocuments;
    private List<CommentDTO> comments;

    // 엔티티 -> DTO 변환 메서드
    public static ProjectDTO fromEntity(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(project.getProjectId());
        dto.setProjectNumber(project.getProjectNumber());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setGoalAmount(project.getGoalAmount());
        dto.setCurrentAmount(project.getCurrentAmount());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setProjectStatus(project.getProjectStatus());
        dto.setRepresentativeImage(project.getRepresentativeImage());
        dto.setReviewApproval(project.isReviewApproval());

        return dto;
    }
}
