package com.teamsking.api.dto.open;


import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class OpenAssistantDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer userId;
    private String userName;
    private Integer courseInformation;
    private Integer resourceLibrary;
    private Integer statisticalAnalysis;
    private Integer curriculumForum;
    private Integer classAttribute;
    private Integer lectureNotes;
    private Integer coverFlowe;
    private Integer teacherTeam;
    private Integer localTeachers;
    private Integer courseware;
    private Integer column;
    private Integer notice;
    private Integer resourceUploading;
    private Integer assistant;
    private Integer student;
    private Integer group;
    private Integer objectivePractice;
    private Integer subjectiveExercise;
    private Integer classDiscussion;
    private Integer discussionArea;
    private Integer questionnaire;
    private Integer onlineExamination;
    private Integer onlineAchievement;
    private Integer comprehensiveAchievement;
    private Integer finePerformance;
    private Integer closeCourse;
    private Integer issuingCertificates;
    private Integer progressSurvey;
    private Integer analysisCourse;
    private Integer learningAnalysis;
    private Integer teachingAnalysis;
    private Integer releaseCourse;
    private Integer exportCourse;
    private Integer underLineExamination;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
