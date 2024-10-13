package org.example.swllow_data_analysis.model;

import lombok.Data;

@Data
public class Swllow {
    private double longitude;
    private double latitude;
    private double longitudeDifference;
    private double latitudeDifference;
    private double distance;
}
