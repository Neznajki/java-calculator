package com.example.calculator.service;

import com.example.calculator.dto.CalcOptionsDTO;

public class ExtractorService {
  public CalcOptionsDTO extract(String[] args) {
    return new CalcOptionsDTO(Float.parseFloat(args[0]), Float.parseFloat(args[1]), args[2]);
  }
}
