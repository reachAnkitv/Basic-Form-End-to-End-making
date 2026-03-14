package com.api.making;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/apicall")
public class MyController {

    @Autowired
    Myrepository repo;

    @GetMapping("/get")
    public List<Details> give(){
        return repo.findAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Details input){
        repo.save(input);
        return "successfully added";
    }

    @PutMapping("/update/{idx}")
    public String update(@PathVariable Long idx,@RequestBody Details newdetail){
        if(repo.existsById(idx)){
            newdetail.setId(idx);
            repo.save(newdetail);
            return "successfully updated";
        }
        else return "data not present";

    }

    @DeleteMapping("/del/{num}")
    public String delete(@PathVariable Long num){
        if(repo.existsById(num)) {
            repo.deleteById(num);
            return "deleted successfully";
        }
        return "invalid index";
    }


}
