package org.example.swllow_data_analysis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@Entity
public class Swallow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long number;
    private String First;
    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;

    public Swallow(
            long index,
            String First,
            double longitude,
            double latitude,
            double longitudeDifference,
            double latitudeDifference,
            double distance) {

        this.number = index;
        this.First = First;
        this.longitude = longitude;
        this.latitude = latitude;
        this.longitudeDifference = longitudeDifference;
        this.latitudeDifference = latitudeDifference;
        this.distance = distance;
    }

    @Override
    public String toString(){
        return number + "\n"
                + First + "\n"
                + longitude + "\n"
                + longitude + "\n"
                + longitudeDifference + "\n"
                + latitudeDifference + "\n"
                + distance + "\n";
    }
}
