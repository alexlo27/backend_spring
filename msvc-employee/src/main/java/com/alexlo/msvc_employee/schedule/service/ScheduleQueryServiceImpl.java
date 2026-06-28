package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.response.EmployeeScheduleHoursDTO;
import com.alexlo.msvc_employee.schedule.model.WorkScheduleEntity;
import com.alexlo.msvc_employee.schedule.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Transactional(readOnly = true)
    @Override
    public Map<Long, EmployeeScheduleHoursDTO> findHoursBySchedulePeriod(Long schedulePeriodId) {
        List<WorkScheduleEntity> schedules = workScheduleRepository.findBySchedulePeriodId(schedulePeriodId);

        Map<Long, HoursAccumulator> accumMap = new LinkedHashMap<>();

        for (WorkScheduleEntity ws : schedules) {
            Long empId = ws.getEmployee().getId();
            HoursAccumulator acc = accumMap.computeIfAbsent(empId, k -> new HoursAccumulator());

            long totalMinutes = Duration.between(ws.getStartTime(), ws.getEndTime()).toMinutes();
            if (totalMinutes < 0) {
                totalMinutes += 1440;
            }
            if (ws.getStartBreak() != null && ws.getEndBreak() != null) {
                long breakMinutes = Duration.between(ws.getStartBreak(), ws.getEndBreak()).toMinutes();
                if (breakMinutes < 0) {
                    breakMinutes += 1440;
                }
                totalMinutes -= breakMinutes;
            }

            acc.totalMinutes += totalMinutes;
            acc.dates.add(ws.getDate());
        }

        Map<Long, EmployeeScheduleHoursDTO> result = new LinkedHashMap<>();
        for (Map.Entry<Long, HoursAccumulator> entry : accumMap.entrySet()) {
            result.put(entry.getKey(), new EmployeeScheduleHoursDTO(
                    entry.getKey(),
                    entry.getValue().totalMinutes / 60.0,
                    entry.getValue().dates.size()
            ));
        }
        return result;
    }

    private static class HoursAccumulator {
        long totalMinutes = 0;
        Set<LocalDate> dates = new HashSet<>();
    }
}
