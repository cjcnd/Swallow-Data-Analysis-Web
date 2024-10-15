package org.example.swllow_data_analysis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Data
public class SwallowTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date placeAt;
    @OneToMany(targetEntity = Swallow.class)
    private List<Swallow> swallows = new ArrayList<>();

    public SwallowTable(Date placeAt, List<Swallow> swallows) {
        this.placeAt = placeAt;
        this.swallows = swallows;
    }
}
