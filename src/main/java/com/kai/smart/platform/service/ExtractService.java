package com.kai.smart.platform.service;

import com.kai.smart.platform.extract.KaiDataExtractor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExtractService {
    @Async
    public void doExtract(KaiDataExtractor extractor){
        extractor.extract();
    }
}
