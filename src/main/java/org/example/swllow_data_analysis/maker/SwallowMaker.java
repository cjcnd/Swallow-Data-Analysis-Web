package org.example.swllow_data_analysis.maker;

import org.example.swllow_data_analysis.mathFuntion.MathFunction;
import org.example.swllow_data_analysis.model.Swallow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

@Component
public class SwallowMaker implements ObjectMaker {

    private MathFunction mathFunction;

    @Autowired
    public SwallowMaker(MathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }

    @Override
    public Object objectMaker(String[] swallow, List<Integer> indexList, double ...preAgr) {
        boolean isNan = false;

        if (indexList.size() < 3) {
            return null;
        }

        ListIterator<Integer> indexListIterator = indexList.listIterator();
        Swallow swallowIn = new Swallow();

        String first = swallow[indexListIterator.next()];
        double longitude, latitude;
        try {
            longitude = Double.parseDouble(swallow[indexListIterator.next()]);
            latitude = Double.parseDouble(swallow[indexListIterator.next()]);
        } catch (NumberFormatException e) {
            return null;
        }
        double longitudeDifference = longitude - preAgr[0];
        double latitudeDifference = latitude - preAgr[1];
        double distance = mathFunction.mathFunction(latitude, preAgr[1], longitude, preAgr[0]);

        swallowIn.setFirst(first);
        swallowIn.setLongitude(longitude);
        swallowIn.setLatitude(latitude);
        swallowIn.setLatitudeDifference(latitudeDifference);
        swallowIn.setLongitudeDifference(longitudeDifference);
        swallowIn.setDistance(distance);

        return swallowIn;
    }
}
