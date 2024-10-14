package org.example.swllow_data_analysis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Swallow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;

}
