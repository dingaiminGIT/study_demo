package date;

import org.springframework.scheduling.TaskScheduler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Oecp {

    public static void main(String[] args) {
        // 异步把错误码信息入到 ES 中
        /*CompletableFuture.runAsync(() -> {
            String result = null;
            try {
                result = oecpErrorInfoEsManager.editErrorCode(OecpErrorDocumentUtils.convertToOecpErrorDocument(oecpErrorInfo));
            } catch (IOException e) {
                logger.error("编辑错误码信息同步到 ES 中发生异常，错误码 {}，", oecpErrorInfo.getCode(), e);
            }
            logger.info("更新错误码异步更新到 ES 响应结果 {}", result);
        }, TaskScheduler.ioExecutor);*/
        System.out.println();
        System.out.println();

    }
}
