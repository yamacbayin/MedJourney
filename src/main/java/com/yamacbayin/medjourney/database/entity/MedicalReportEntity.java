package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_reports")
public class MedicalReportEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @OneToOne(mappedBy = "report")
    private AppointmentEntity appointment;

    private String note;

/*    @Temporal(TemporalType.TIMESTAMP)
    private Date date;*/

}
