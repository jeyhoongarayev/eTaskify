package com.abb.etaskify.db.entity;


import com.abb.etaskify.model.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    String id;

    String title;
    String description;

    @JsonFormat(pattern = "dd/mm/yyyy" )
    LocalDate deadline;

    @Enumerated(value = EnumType.STRING)
    TaskStatus taskStatus;
    String idOrganization;
}
