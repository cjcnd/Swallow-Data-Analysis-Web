package org.example.swllow_data_analysis.preprocessing;

import org.example.swllow_data_analysis.model.Swallow;
import org.example.swllow_data_analysis.repository.SwallowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Preprocessing {

    private final SwallowRepository swallowRepository;

    @Autowired
    public Preprocessing(SwallowRepository swallowRepository) {
        this.swallowRepository = swallowRepository;
    }

    public Swallow preprocessing(Swallow swallow) {
        return new Swallow();
    }
}
