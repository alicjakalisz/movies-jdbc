package com.movieJDBC.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/fake")
public class DataFakerController {

    private final DataFakeService dataFakeService;

    @Autowired
    public DataFakerController(DataFakeService dataFakeService) {
        this.dataFakeService = dataFakeService;
    }

    /**
     * /api/v1/fake?actorCount=5&movieCount=3
     * @param actorCount
     * @param movieCount
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Void> populateData(@RequestParam() Integer actorCount, @RequestParam() Integer movieCount){
        dataFakeService.fake(actorCount, movieCount);
        return ResponseEntity.ok().build();
    }
}
