package hk.edu.hkmu.jiang.javafinal.interfaces.job;

import hk.edu.hkmu.jiang.javafinal.application.service.InspectionApplicationService;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryInspectionJob extends QuartzJobBean {

    @NonNull
    private InspectionApplicationService inspectionApplicationService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        InventoryType type = InventoryType.valueOf(
                context.getMergedJobDataMap().get(InventoryType.class.getName()).toString());

        log.info("[Inspection job execution ] time: {}, parameter: {}",
                java.time.LocalDateTime.now(), type);

        try {
            inspectionApplicationService.inspect(type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}