package com.movieJDBC.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/fake")
public class DataFakerController {

    private final DataFakeService dataFakeService;

    @Autowired
    public DataFakerController(DataFakeService dataFakeService) {
        this.dataFakeService = dataFakeService;
    }

    @GetMapping("/movies/{count}")
    public int insertMovieRows(@PathVariable("count") Integer count){
        return  dataFakeService.fakeMovieData(count);
    }
    @GetMapping("/actors/{count}")
    public int insertActorsRows(@PathVariable("count") Integer count){
        return  dataFakeService.fakeActorData(count);
    }
}
