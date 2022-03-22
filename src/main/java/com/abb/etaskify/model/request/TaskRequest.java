package com.abb.etaskify.model.request;

import com.abb.etaskify.model.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {

    String title;
    String description;
    @JsonFormat(pattern = "dd/mm/yyyy" )
    LocalDate deadline;
    @Enumerated(value = EnumType.STRING)
    TaskStatus taskStatus;
    int idFrom;
    int idTo;
    int idOrganization;

}
