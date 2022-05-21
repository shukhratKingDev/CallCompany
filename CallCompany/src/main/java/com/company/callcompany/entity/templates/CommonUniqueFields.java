package com.company.callcompany.entity.templates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonUniqueFields {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy="org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    @CreatedBy
    private UUID employee_id;
}
