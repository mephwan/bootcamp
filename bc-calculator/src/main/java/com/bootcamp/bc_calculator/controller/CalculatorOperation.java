package com.bootcamp.bc_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_calculator.dto.CalculateResult;
import com.bootcamp.bc_calculator.model.CalOperation;
import com.bootcamp.bc_calculator.model.CalculateRequest;

public interface CalculatorOperation {

  @GetMapping(value = "/operation")
  CalculateResult calculateByParam(@RequestParam String x, @RequestParam String y, @RequestParam CalOperation operation);
  
  @PostMapping(value = "/operation")
  CalculateResult calculate(@RequestBody CalculateRequest calculateRequest);

  @GetMapping(value = "/operation/{x}/{y}/{operation}")
  CalculateResult calculateByPath(@PathVariable String x, @PathVariable String y, @PathVariable CalOperation operation);
}
