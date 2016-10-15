package com.gmail.at.ivanehreshi.findopponent;

import com.gmail.at.ivanehreshi.findopponent.domain.OpponentFinder;
import com.gmail.at.ivanehreshi.findopponent.domain.OpponentFinderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RootConfig {

    @Bean
    @Scope
    public OpponentFinder opponentFinder() {
        return new OpponentFinderImpl();
    }




}
