package org.example.swllow_data_analysis.service.readfile;

import org.example.swllow_data_analysis.mathFuntion.MathFunction;
import org.example.swllow_data_analysis.model.Swallow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Service
public class TextFileService implements FileService {

    private final MathFunction mathFunction;

    @Autowired
    private TextFileService(MathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }

    @Override
    public List<Swallow> ReadFile(String fileName) {
        List<Swallow> swallowList = new ArrayList<>();

        try {
            Path uploadFile = Paths.get(root_dir, "upload_dir", fileName);
            Scanner scanner = new Scanner(uploadFile.toFile());
            scanner.useDelimiter("\n");
            String[] swallowIndex;
            String[] swallowPre = null;
            int[] indexList = null;

            if (scanner.hasNext()) {
                swallowIndex = scanner.next().split("\t");
                indexList = new int[swallowIndex.length];
                int j = 0;

                for (int i  = 0; i < swallowIndex.length; i++) {
                    if (Objects.equals(swallowIndex[i], "Index") || Objects.equals(swallowIndex[i], "First") || Objects.equals(swallowIndex[i], "Longitude") || Objects.equals(swallowIndex[i], "Latitude")) {
                        indexList[j] = i;
                        j++;
                    }
                }

                swallowPre = scanner.next().split("\t");

            }
            while (scanner.hasNext()) {
                String[] swallow = scanner.next().split("\t");

                swallowList.add(new Swallow(
                        Long.parseLong(swallow[indexList[0]]),
                        swallow[indexList[1]],
                        Double.parseDouble(swallow[indexList[6]]),
                        Double.parseDouble(swallow[indexList[7]]),
                        Double.parseDouble(swallow[indexList[6]]) - Double.parseDouble(swallowPre[indexList[6]]),
                        Double.parseDouble(swallow[indexList[7]]) - Double.parseDouble(swallowPre[indexList[7]]),
                        mathFunction.mathFunction(Double.parseDouble(swallow[indexList[6]]), Double.parseDouble(swallow[indexList[7]]), Double.parseDouble(swallowPre[indexList[6]]), Double.parseDouble(swallowPre[indexList[7]]))
                        ));

                swallowPre = swallow;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return swallowList;
    }

    @Override
    public void MakeCsvFile(String fileName) {

    }
}
