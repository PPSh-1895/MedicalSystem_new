package com.lin.appapidemo.controller.shixun;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.appapidemo.mapper.shixun.*;
import com.lin.appapidemo.model.shixun.*;
import com.lin.appapidemo.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excise")
public class ExciseController {
    @Autowired(required = false)
    private PatientMapper patientMapper;
    @Autowired(required = false)
    private DrugMapper drugMapper;

    @RequestMapping(value = "/patient_name_search")
    public Map<String,Object> patient_name_search(@RequestParam("patient_id")int patient_id)
    {
        Map<String,Object> map=new HashMap<>();
        Patient printed = patientMapper.selectByPatientId(patient_id);
        map.put("name",printed.getPatient_name());
        return map;
    }

    @RequestMapping(value = "stock_change")
    public Map<String, Object> stock_change(@RequestParam("new_stock") int new_stock, @RequestParam("drug_id") int drug_id)
    {
        Map<String, Object> map = new HashMap<>();
        drugMapper.Update_Stock(new_stock,drug_id);
        map.put("result","success!");
        return map;
    }

    @RequestMapping(value = "stock_search")
    public Map<String, Object> stock_search(@RequestParam("drug_id") int drug_id)
    {
        Map<String, Object> map = new HashMap<>();
        Drug printed = drugMapper.selectByDrugId(drug_id);
        map.put("stock",printed.getStock());
        return map;
    }

    @RequestMapping(value = "insert_newDrug")
    public Map<String, Object> insert_newDrug(@RequestParam("drug_name")  String drug_name)
    {
        Map<String, Object> map = new HashMap<>();
        drugMapper.insert(new Drug(drug_name));
        map.put("result","success!");
        return map;
    }
    public static void main()
    {
        SpringApplication.run(ExciseController.class);
    }
}
