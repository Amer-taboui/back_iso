package com.crm.operis_app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Configuration
public class  YearDetectorGen {
    @Bean
    public String YearDetector() {
    DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
    String formattedDate = df.format(Calendar.getInstance().getTime());
    return formattedDate;

    }
}
