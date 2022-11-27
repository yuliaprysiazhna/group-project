package pwo.group.project.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonDeserialize(builder = Training.TrainingBuilder.class)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Training {

    private final String name;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime dateTime;

    private final Integer minutesLength;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TrainingBuilder {

    }

}
