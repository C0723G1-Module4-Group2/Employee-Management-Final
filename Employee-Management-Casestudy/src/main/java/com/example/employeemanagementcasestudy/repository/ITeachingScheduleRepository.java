package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.TeachingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITeachingScheduleRepository extends JpaRepository<TeachingSchedule, Integer> {
    @Query(value = "SELECT ts.*,c.class_name,t.teacher_name,ti.start_time,ti.end_time FROM teaching_schedule ts \n" +
            "join classes c on c.class_id = ts.class_id\n" +
            "join teacher t on t.teacher_id = ts.teacher_id\n" +
            "join time_sheets ti on ti.time_sheet_id = ts.time_sheet_id;", nativeQuery = true)
    <TeachingScheduleDto> List<TeachingScheduleDto> getAll();

    @Query(value = "SELECT ts.* FROM time_sheets ts LEFT JOIN teaching_schedule t ON ts.time_sheet_id = t.time_sheet_id \n" +
            "               AND t.class_id = :classId" +
            "                AND (((:startDate BETWEEN t.start_date and t.end_date)\n" +
            "                        or (:endDate BETWEEN t.start_date and t.end_date)\n" +
            "                        or (t.start_date BETWEEN :startDate and :endDate)) \n" +
            "                        or (start_date is null and end_date is null)) \n" +
            "                        WHERE t.time_sheet_id IS NULL; ", nativeQuery = true)
    <TimeSheetDto> List<TimeSheetDto> getTimeSheetsFree(@Param("classId") int classId,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = "SELECT ( COUNT ( teaching_schedule.teaching_schedule_id) > 0) FROM teaching_schedule ts " +
            "       WHERE ts.teacher_id = :teacherId " +
            "       AND ts.class_id = :classId " +
            "       AND ((ts.start_date BETWEEN :newStartDate AND :newEndDate) " +
            "       OR (ts.end_date BETWEEN :newStartDate AND :newEndDate) " +
            "       OR (:newStartDate BETWEEN ts.start_date AND ts.end_date) " +
            "       OR (:newEndDate BETWEEN ts.start_date AND ts.end_date))")
    boolean isScheduleOverlapping(@Param("teacherId") int classId,
                                  @Param("classId") int teacherId,
                                  @Param("newStartDate") LocalDate newStartDate,
                                  @Param("newEndDate") LocalDate newEndDate);

    @Query(value = "SELECT t.teacher_id, t.teacher_name FROM teacher t LEFT JOIN teaching_schedule ts ON ts.teacher_id = t.teacher_id \n" +
            "                      AND ts.class_id  is not null" +
            "                        AND ((:startDate BETWEEN ts.start_date and ts.end_date)" +
            "                        or (:endDate BETWEEN ts.start_date and ts.end_date))" +
            "                        AND ts.time_sheet_id =:timeSheetId" +
            "                      WHERE ts.teacher_id IS NULL; ", nativeQuery = true)
    <TeacherDto> List<TeacherDto> getTeacherFree(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate,
                                                 @Param("timeSheetId") int timeSheetId);

    @Query(value = "SELECT ts.* FROM time_sheets ts LEFT JOIN teaching_schedule t ON ts.time_sheet_id = t.time_sheet_id \n" +
            "               AND t.class_id = :classId" +
            "                AND (((:startDate BETWEEN t.start_date and t.end_date)\n" +
            "                        or (:endDate BETWEEN t.start_date and t.end_date)\n" +
            "                        or (t.start_date BETWEEN :startDate and :endDate)) \n" +
            "                        or (start_date is null and end_date is null)) \n" +
            "                        WHERE t.time_sheet_id IS NULL or t.time_sheet_id = (select time_sheet_id from teaching_schedule where teaching_schedule_id =:teachingScheduleId ) ", nativeQuery = true)
    <TimeSheetDto> List<TimeSheetDto> getTimeSheetsFreeForUpdate(@Param("classId") int classId,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate,
                                                                 @Param("teachingScheduleId") int teachingScheduleId);

    @Query(value = "SELECT t.teacher_id, t.teacher_name FROM teacher t LEFT JOIN teaching_schedule ts ON ts.teacher_id = t.teacher_id \n" +
            "                AND ts.class_id  is not null" +
            "                 AND ((:startDate BETWEEN ts.start_date and ts.end_date)" +
            "                        or (:endDate BETWEEN ts.start_date and ts.end_date))" +
            "                 AND ts.time_sheet_id =:timeSheetId" +
            "                 WHERE ts.teacher_id IS NULL or ts.teacher_id = (select teacher_id from teaching_schedule where teaching_schedule_id =:teachingScheduleId ) ", nativeQuery = true)
    <TeacherDto> List<TeacherDto> getTeacherFreeForUpdate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("timeSheetId") int timeSheetId,
            @Param("teachingScheduleId") int teachingScheduleId);
}
