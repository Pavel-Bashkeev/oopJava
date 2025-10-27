package ru.bashkeev.spring.stream;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StreamingPlatform {

    private final DataReader reader;
    private final DataWriter writer;
    private final List<DataProcessor> processors;

    public StreamingPlatform(DataReader reader, DataWriter writer, List<DataProcessor> processors) {
        this.reader = reader;
        this.writer = writer;
        this.processors = processors;
    }

    public void execute(String inputFile, String outputFile) {
        List<String> data = reader.read(inputFile);

        for (DataProcessor processor : processors) {
            data = processor.process(data);
        }

        writer.write(outputFile, data);
    }
}
