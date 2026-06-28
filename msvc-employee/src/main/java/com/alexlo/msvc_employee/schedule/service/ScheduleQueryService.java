package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.response.EmployeeScheduleHoursDTO;

import java.util.Map;

public interface ScheduleQueryService {

    Map<Long, EmployeeScheduleHoursDTO> findHoursBySchedulePeriod(Long schedulePeriodId);
}
