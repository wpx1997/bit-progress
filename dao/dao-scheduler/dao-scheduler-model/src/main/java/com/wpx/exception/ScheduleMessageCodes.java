package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 *  定时任务异常信息
 */
public class ScheduleMessageCodes {

    public static final String QUARTZJOB_CREATE_EXCEPTION_MESSAGE = "定时任务创建异常";
    public static final String QUARTZJOB_NAME_EMPTY_EXCEPTION_MESSAGE = "定时任务名称为空";
    public static final String QUARTZJOB_CRON_EMPTY_EXCEPTION_MESSAGE = "定时任务cron表达式为空";
    public static final String QUARTZJOB_TYPE_EMPTY_EXCEPTION_MESSAGE = "定时任务类型为空";
    public static final String QUARTZJOB_QUERY_EXCEPTION_MESSAGE = "定时任务查询异常";
    public static final String QUARTZJOB_REMOVE_EXCEPTION_MESSAGE = "定时任务移除异常";
    public static final String QUARTZJOB_CHECK_EXISTS_EXCEPTION_MESSAGE = "定时任务查重异常";
    public static final String JOBKEY_QUERY_EXCEPTION_MESSAGE = "jobKey查询异常";
    public static final String TRIGGER_PAUSE_EXCEPTION_MESSAGE = "触发器暂停异常";
    public static final String TRIGGER_RESCHEDULE_EXCEPTION_MESSAGE = "触发器恢复异常";
    public static final String JOBGROUP_QUERY_EXCEPTION_MESSAGE = "查询任务分组异常";

}
