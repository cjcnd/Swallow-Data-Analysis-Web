package org.example.swllow_data_analysis.service;

import lombok.extern.slf4j.Slf4j;
import org.example.swllow_data_analysis.mathFuntion.MathFunction;
import org.example.swllow_data_analysis.maker.ObjectMaker;
import org.example.swllow_data_analysis.model.Swallow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TextFileService implements FileService {

    private final MathFunction mathFunction;
    private final List<String> headList;
    private ObjectMaker objectMaker;

    @Autowired
    private TextFileService(MathFunction mathFunction, @Value("${spring.text.head.list}") String[] headList, ObjectMaker objectMaker) {
        this.mathFunction = mathFunction;
        this.headList = Arrays.stream(headList)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        this.objectMaker = objectMaker;
    }

    @Override
    public List<Swallow> ReadFile(String fileName) {
        List<Swallow> swallowList = new ArrayList<>();

        try {
            Path uploadFile = Paths.get(root_dir, "upload_dir", fileName);
            Scanner scanner = new Scanner(uploadFile.toFile());
            List<Integer> indexList = new ArrayList<>();
            scanner.useDelimiter("\n");

            if (scanner.hasNext()) {
                String[] swallowHeader = scanner.next().split("\t");

                for (int i = 0; i < swallowHeader.length; i++) {
                    if (headList.contains(swallowHeader[i])) {
                        indexList.add(Integer.valueOf(i));
                    }
                }

                log.info(indexList.toString());
                log.info(Arrays.toString(swallowHeader));

            } else {
                return null;
            }

            while (scanner.hasNext()) {
                String[] swallow = scanner.next().split("\t");

                Swallow swallowIn = (Swallow) objectMaker.objectMaker(swallow, indexList.toArray());

                swallowList.add(swallowIn);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return swallowList;
    }

    @Override
    public void MakeCsvFile(String fileName) {

    }

    private Swallow MakeSwallow(Scanner scanner, int[] indexList, int len) {
        for (int index : indexList) {
            for (int i = 0; i < len; i++) {
                if (index == ) {
                    continue;
                }
            }
        }
    }
}
