package hk.edu.hkmu.jiang.javafinal.Infrastructure.config.quartz;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import hk.edu.hkmu.jiang.javafinal.interfaces.job.InventoryInspectionJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail shelfInventoryInspectionJob() {
        return JobBuilder.newJob(InventoryInspectionJob.class)
                .withIdentity("shelfInventoryInspectionJob", "inspection")
                .usingJobData(InventoryType.class.getName(), InventoryType.SHELF.toString())
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger shelfInventoryInspectionJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(shelfInventoryInspectionJob())
                .withIdentity("shelfInventoryInspectionJobTrigger", "inspection")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail warehouseInventoryInspectionJob() {
        return JobBuilder.newJob(InventoryInspectionJob.class)
                .withIdentity("warehouseInventoryInspectionJob", "inspection")
                .usingJobData(InventoryType.class.getName(), InventoryType.WAREHOUSE.toString())
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger warehouseInventoryInspectionJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(warehouseInventoryInspectionJob())
                .withIdentity("warehouseInventoryInspectionJobTrigger", "inspection")
                .withSchedule(scheduleBuilder)
                .build();
    }


}