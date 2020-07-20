package com.acme.surfswap.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/async")
public class AsyncController {
    @GetMapping("/deferred")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult() {
        log.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.info("servlet thread freed");
        return output;
    }

    @GetMapping("/test")
    public DeferredResult<ResponseEntity<String>> handleWithTimeout(){
        Long timeOutInMilliSec = 4000L;
        log.info("processing task...");
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(timeOutInMilliSec,new ResponseEntity<>("Task timeout!", HttpStatus.REQUEST_TIMEOUT));
        CompletableFuture.runAsync(()->{
            try {
                //Long pooling task; If task is not completed within 100 sec timeout response return for this request
                TimeUnit.SECONDS.sleep(5);
                //set result after completing task to return response to client
                log.info("task complete!");
                ResponseEntity<String> taskFinished = new ResponseEntity<>("Task finished", HttpStatus.OK);
                deferredResult.setResult(taskFinished);
            }catch (Exception ex){
                log.info("exception\n " + ex);
                ResponseEntity<String> taskExceptionThrown = new ResponseEntity<>("Task exception!", HttpStatus.NOT_FOUND);
                deferredResult.setResult(taskExceptionThrown);
            }
        });
        log.info("tread freed");
        return deferredResult;
    }
}
