package com.wpx.quartz.job;

import com.wpx.feignclient.FeignClientService;
import com.wpx.model.quartzjob.QuartzJob;
import com.wpx.model.quartzjob.envm.TriggerType;
import com.wpx.quartz.factory.QuartzFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author 不会飞的小鹏
 * @Description: 任务触发
 **/
@Component
public class CustomJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(CustomJob.class);

    @Autowired
    private QuartzFactory quartzFactory;

    @Autowired
    private FeignClientService feignClientService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        QuartzJob quartzJob = (QuartzJob) jobExecutionContext.getMergedJobDataMap().get("quartzJob");
        String jobName = quartzJob.getJobName();

        //triggerType
        TriggerType triggerType = quartzJob.getTriggerType();

        try {
            /* 反射调用方法 */
            // 2021-2-6 修改为服务调用
            String url = quartzJob.getServerUrl();
            URI uri = URI.create(url);
            feignClientService.createPostFeignClient(uri, quartzJob);
            logger.info("[{}] 调用完成", jobName);
        } catch (Exception e) {
            logger.error("exception：", e);
            logger.info("[{}] 调用失败", jobName);

            //根据任务定义，只对普通任务实施重试
            //重试次数为最大为三次
            if (triggerType.equals(TriggerType.SIMPLE) && quartzJob.getRetryTime() < 3) {
                //重试次数递增
                quartzJob.setRetryTime(quartzJob.getRetryTime() + 1);
                quartzFactory.reTrySimpleJob(quartzJob);
            }
        }
    }

}