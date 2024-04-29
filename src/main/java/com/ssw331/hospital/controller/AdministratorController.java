package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.Administrator;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WWWsy
 */
@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public Result<Object> getAdministrators() {
        List<Administrator> administrators = administratorService.getAllAdministrators();
        return ResultResponse.success(administrators);
    }

    @GetMapping("/{id}")
    public Result<Object> getAdministratorById(@PathVariable String id) {
        Administrator administrator = administratorService.getAdministratorById(id);
        if (administrator == null) {
            return ResultResponse.failure();
        }
        return ResultResponse.success(administrator);
    }

    @GetMapping("/name")
    public Result<Object> getAdministratorByName(@RequestParam String name) {
        List<Administrator> administrators = administratorService.getAdministratorByName(name);
        if (administrators.isEmpty()) {
            return ResultResponse.failure();
        }
        return ResultResponse.success(administrators);
    }

    @PostMapping("/add")
    public Result<Object> addAdministrator(@RequestBody Administrator administrator) {
        Administrator addedAdministrator = administratorService.addAdministrator(administrator);
        return ResultResponse.success(addedAdministrator);
    }


    @PutMapping("/update")
    public Result<Object> updateAdministrator(@RequestBody Administrator administrator) {
        Administrator updatedAdministrator = administratorService.updateAdministrator(administrator);
        return ResultResponse.success(updatedAdministrator);
    }
}
