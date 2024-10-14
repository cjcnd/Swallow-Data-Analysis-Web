package org.example.swllow_data_analysis.preprocessing;

import org.example.swllow_data_analysis.model.Swallow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Preprocessing {

    public Swallow preprocessing(Swallow swallow) {
        return swallow;
    }
}
