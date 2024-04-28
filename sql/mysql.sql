/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2024/4/26 21:12:24                           */
/*==============================================================*/


drop table if exists TEST_ACCOUNT.ADMINISTRATOR;

drop table if exists TEST_ACCOUNT.CHATRECORD;

drop table if exists TEST_ACCOUNT.CONSULTATION_INFO;

drop table if exists TEST_ACCOUNT.CONSULTING_ROOM;

drop table if exists TEST_ACCOUNT.DEPARTMENT2;

drop table if exists TEST_ACCOUNT.DOCTOR;

drop table if exists TEST_ACCOUNT.LEAVE_APPLICATION;

drop table if exists TEST_ACCOUNT.MEDICINE_DESCRIPTION;

drop table if exists TEST_ACCOUNT.MEDICINE_OUT;

drop table if exists TEST_ACCOUNT.MEDICINE_PURCHASE;

drop table if exists TEST_ACCOUNT.MEDICINE_SELL;

drop table if exists TEST_ACCOUNT.MEDICINE_STOCK;

drop table if exists TEST_ACCOUNT.OUTPATIENT_ORDER;

drop table if exists TEST_ACCOUNT.PATIENT;

drop table if exists TEST_ACCOUNT.PRESCRIPTION;

drop table if exists TEST_ACCOUNT.PRESCRIPTION_MEDICINE;

drop table if exists TEST_ACCOUNT.REGISTRATION;

drop table if exists TEST_ACCOUNT.TEMPLATE;

drop table if exists TEST_ACCOUNT.TREATMENT_FEEDBACK;

drop table if exists TEST_ACCOUNT.TREATMENT_RECORD;

drop table if exists TEST_ACCOUNT.TREATMENT_RECORD2;

/*==============================================================*/
/* User: TEST_ACCOUNT                                           */
/*==============================================================*/
create user TEST_ACCOUNT;

/*==============================================================*/
/* Table: ADMINISTRATOR                                         */
/*==============================================================*/
create table TEST_ACCOUNT.ADMINISTRATOR
(
    ADMINISTRATOR_ID     varchar(20) not null,
    NAME                 varchar(20),
    GENDER               numeric(1,0),
    BIRTHDATE            datetime,
    CONTACT              varchar(20),
    PASSWORD             varchar(200),
    primary key (ADMINISTRATOR_ID)
);

/*==============================================================*/
/* Table: CHATRECORD                                            */
/*==============================================================*/
create table TEST_ACCOUNT.CHATRECORD
(
    RECORDID             varchar(30),
    DOCTOR_ID            varchar(20) not null,
    PATIENT_ID           varchar(20) not null,
    MESSAGE              varchar(2000),
    SENDER_TYPE          numeric(8,0),
    TIMESTAMP            timestamp not null,
    READ_STATUS          numeric(8,0),
    primary key (DOCTOR_ID, PATIENT_ID, TIMESTAMP)
);

/*==============================================================*/
/* Table: CONSULTATION_INFO                                     */
/*==============================================================*/
create table TEST_ACCOUNT.CONSULTATION_INFO
(
    DOCTOR_ID            varchar(10) not null,
    CLINIC_NAME          varchar(200) not null,
    DATE_TIME            datetime not null,
    "PERIOD"               numeric(8,0) not null,
    primary key (DOCTOR_ID, CLINIC_NAME, DATE_TIME, PERIOD)
);

/*==============================================================*/
/* Table: CONSULTING_ROOM                                       */
/*==============================================================*/
create table TEST_ACCOUNT.CONSULTING_ROOM
(
    CONSULTING_ROOM_NAME varchar(30) not null,
    CONSULTANT_CAPACITY  numeric(8,0),
    primary key (CONSULTING_ROOM_NAME)
);

/*==============================================================*/
/* Table: DEPARTMENT2                                           */
/*==============================================================*/
create table TEST_ACCOUNT.DEPARTMENT2
(
    DEPARTMENT_NAME      varchar(80) not null,
    DEPARTMENT_DESCRIPTION varchar(800),
    primary key (DEPARTMENT_NAME)
);

/*==============================================================*/
/* Table: DOCTOR                                                */
/*==============================================================*/
create table TEST_ACCOUNT.DOCTOR
(
    DOCTOR_ID            varchar(20) not null,
    NAME                 varchar(20),
    GENDER               numeric(1,0),
    BIRTHDATE            datetime,
    TITLE                varchar(20),
    CONTACT              varchar(20),
    SECONDARY_DEPARTMENT varchar(50),
    PHOTOURL             varchar(600),
    PASSWORD             varchar(200),
    SKILLEDIN            varchar(1000),
    primary key (DOCTOR_ID)
);

/*==============================================================*/
/* Table: LEAVE_APPLICATION                                     */
/*==============================================================*/
create table TEST_ACCOUNT.LEAVE_APPLICATION
(
    LEAVE_NOTE_ID        varchar(30) not null,
    LEAVE_APPLICATION_TIME timestamp,
    LEAVE_START_DATE     timestamp,
    LEAVE_END_DATE       timestamp,
    LEAVE_NOTE_REMARK    varchar(10),
    primary key (LEAVE_NOTE_ID)
);

/*==============================================================*/
/* Table: MEDICINE_DESCRIPTION                                  */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_DESCRIPTION
(
    MEDICINE_NAME        varchar(100) not null,
    MEDICINE_TYPE        varchar(40),
    APPLICABLE_SYMPTOM   varchar(600),
    VULGO                varchar(200),
    SPECIFICATION        varchar(500),
    SINGLEDOSE           varchar(300),
    ADMINISTRATION       varchar(400),
    ATTENTION            varchar(400),
    FREQUENCY            varchar(200),
    primary key (MEDICINE_NAME)
);

/*==============================================================*/
/* Table: MEDICINE_OUT                                          */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_OUT
(
    MEDICINE_NAME        varchar(40) not null,
    MANUFACTURER         varchar(40) not null,
    PRODUCTION_DATE      datetime not null,
    PURCHASE_AMOUNT      numeric(8,0) not null,
    DELIVER_DATE         timestamp not null,
    PATIENT_ID           varchar(20) not null,
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE, PURCHASE_AMOUNT, DELIVER_DATE, PATIENT_ID),
    check (PURCHASE_AMOUNT > 0)
);

/*==============================================================*/
/* Table: MEDICINE_PURCHASE                                     */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_PURCHASE
(
    MEDICINE_NAME        varchar(40) not null,
    MANUFACTURER         varchar(460) not null,
    PRODUCTION_DATE      datetime not null,
    PURCHASE_DATE        datetime not null,
    ADMINISTRATOR_ID     varchar(20),
    PURCHASE_AMOUNT      numeric(8,0),
    PURCHASE_PRICE       numeric(6,2),
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE, PURCHASE_DATE),
    check (PURCHASE_PRICE > 0)
);

/*==============================================================*/
/* Table: MEDICINE_SELL                                         */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_SELL
(
    MEDICINE_NAME        varchar(40) not null,
    MANUFACTURER         varchar(80) not null,
    SELLING_PRICE        numeric(6,2),
    primary key (MEDICINE_NAME, MANUFACTURER)
);

/*==============================================================*/
/* Table: MEDICINE_STOCK                                        */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_STOCK
(
    MEDICINE_NAME        varchar(40) not null,
    MANUFACTURER         varchar(40) not null,
    PRODUCTION_DATE      datetime not null ,
    MEDICINE_SHELFLIFE   numeric(8,0),
    MEDICINE_AMOUNT      numeric(8,0),
    THRESHOLD_VALUE      numeric(8,0),
    CLEAN_DATE           datetime,
    CLEAN_ADMINISTRATOR  varchar(20),
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE)
);

/*==============================================================*/
/* Table: OUTPATIENT_ORDER                                      */
/*==============================================================*/
create table TEST_ACCOUNT.OUTPATIENT_ORDER
(
    ORDER_ID             varchar(30) not null comment '????ID',
    PATIENT_ID           varchar(20) comment '??????ID',
    ORDER_TIME           timestamp comment '?��????',
    primary key (ORDER_ID)
);

/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
create table TEST_ACCOUNT.PATIENT
(
    PATIENT_ID           varchar(20) not null,
    NAME                 varchar(20),
    GENDER               numeric(1,0),
    BIRTH_DATE           datetime,
    CONTACT              varchar(20),
    PASSWORD             varchar(200),
    COLLEGE              varchar(40),
    COUNSELLOR           varchar(20),
    primary key (PATIENT_ID)
);

/*==============================================================*/
/* Table: PRESCRIPTION                                          */
/*==============================================================*/
create table TEST_ACCOUNT.PRESCRIPTION
(
    PRESCRIPTION_ID      varchar(40) not null,
    TOTAL_PRICE          numeric(6,2),
    DOCTOR_ID            varchar(20),
    PAYSTATE             numeric(8,0),
    primary key (PRESCRIPTION_ID)
);

/*==============================================================*/
/* Table: PRESCRIPTION_MEDICINE                                 */
/*==============================================================*/
create table TEST_ACCOUNT.PRESCRIPTION_MEDICINE
(
    PRESCRIPTION_ID      varchar(40) not null,
    MEDICINE_NAME        varchar(200) not null,
    MEDICATION_INSTRUCTION varchar(800),
    MEDICINE_PRICE       numeric(6,2),
    QUANTITY             numeric(8,0),
    primary key (PRESCRIPTION_ID, MEDICINE_NAME)
);

/*==============================================================*/
/* Table: REGISTRATION                                          */
/*==============================================================*/
create table TEST_ACCOUNT.REGISTRATION
(
    PATIENT_ID           varchar(20) not null,
    DOCTOR_ID            varchar(20) not null,
    APPOINTMENT_TIME     datetime not null,
    "PERIOD"               numeric(8,0) not null,
    REGISTORDER          numeric(8,0),
    STATE                numeric(8,0) not null,
    PRESCRIPTIONID       varchar(200),
    CHECKIN              numeric(8,0),
    QRCODEURL            varchar(2000),
    ORDERTIME            timestamp,
    primary key (PATIENT_ID, DOCTOR_ID, APPOINTMENT_TIME, STATE, PERIOD)
);

/*==============================================================*/
/* Table: TEMPLATE                                              */
/*==============================================================*/
create table TEST_ACCOUNT.TEMPLATE
(
    PROBLEM              varchar(100),
    ILLNESS              varchar(50),
    COLUMN1              varchar(50),
    SYMPTOM              varchar(50),
    DIAGNOSE             varchar(30),
    PRESCRIPTION         varchar(20),
    MEDICINE             varchar(70),
    NAME                 varchar(40) not null,
    primary key (NAME)
);

/*==============================================================*/
/* Table: TREATMENT_FEEDBACK                                    */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_FEEDBACK
(
    PATIENT_ID           varchar(20),
    DOCTOR_ID            varchar(20),
    TREATMENT_SCORE      numeric(8,0),
    EVALUATION           varchar(200),
    DIAGNOSEDID          varchar(40) not null,
    primary key (DIAGNOSEDID)
);

/*==============================================================*/
/* Table: TREATMENT_RECORD                                      */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_RECORD
(
    DIAGNOSIS_RECORD_ID  varchar(30) not null,
    DOCTOR_ID            varchar(20),
    PATIENT_ID           varchar(20),
    LEAVE_NOTE_ID        varchar(30),
    primary key (DIAGNOSIS_RECORD_ID)
);

/*==============================================================*/
/* Table: TREATMENT_RECORD2                                     */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_RECORD2
(
    DIAGNOSE_ID          varchar(30) not null,
    DIAGNOSE_TIME        timestamp,
    COMMENTSTATE         numeric(8,0),
    SELFREPORTED         varchar(2000),
    PRESENTHIS           varchar(2000),
    ANAMNESIS            varchar(2000),
    SIGN                 varchar(1000),
    CLINICDIA            varchar(2000),
    ADVICE               varchar(2000),
    KINDQUANTITY         numeric(8,0),
    primary key (DIAGNOSE_ID)
);

alter table TEST_ACCOUNT.CHATRECORD add constraint CHATRECORD_FK1 foreign key (DOCTOR_ID)
    references TEST_ACCOUNT.DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.CHATRECORD add constraint CHATRECORD_FK2 foreign key (PATIENT_ID)
    references TEST_ACCOUNT.PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.CONSULTATION_INFO add constraint CONSULTATION_INFO_CONSULT_FK1 foreign key (CLINIC_NAME)
    references TEST_ACCOUNT.CONSULTING_ROOM (CONSULTING_ROOM_NAME) on delete restrict on update restrict;

alter table TEST_ACCOUNT.CONSULTATION_INFO add constraint CONSULTATION_INFO_DOCTOR_FK1 foreign key (DOCTOR_ID)
    references TEST_ACCOUNT.DOCTOR (DOCTOR_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.MEDICINE_OUT add constraint MEDICINE_OUT_PATIENT_FK1 foreign key (PATIENT_ID)
    references TEST_ACCOUNT.PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.MEDICINE_PURCHASE add constraint MEDICINE_PURCHASE_ADMINIS_FK1 foreign key (ADMINISTRATOR_ID)
    references TEST_ACCOUNT.ADMINISTRATOR (ADMINISTRATOR_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.MEDICINE_STOCK add constraint MEDICINE_STOCK_ADMINISTRA_FK1 foreign key (CLEAN_ADMINISTRATOR)
    references TEST_ACCOUNT.ADMINISTRATOR (ADMINISTRATOR_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.OUTPATIENT_ORDER add constraint OUTPATIENT_ORDER_PATIENT_FK1 foreign key (PATIENT_ID)
    references TEST_ACCOUNT.PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.PRESCRIPTION add constraint PRESCRIPTION_DOCTOR_FK1 foreign key (DOCTOR_ID)
    references TEST_ACCOUNT.DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.PRESCRIPTION_MEDICINE add constraint PRESCRIPTION_MEDICINE_MED_FK1 foreign key (MEDICINE_NAME)
    references TEST_ACCOUNT.MEDICINE_DESCRIPTION (MEDICINE_NAME) on delete restrict on update restrict;

alter table TEST_ACCOUNT.PRESCRIPTION_MEDICINE add constraint PRESCRIPTION_MEDICINE_PRE_FK1 foreign key (PRESCRIPTION_ID)
    references TEST_ACCOUNT.PRESCRIPTION (PRESCRIPTION_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.REGISTRATION add constraint REGISTRATION_DOCTOR_FK1 foreign key (DOCTOR_ID)
    references TEST_ACCOUNT.DOCTOR (DOCTOR_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.REGISTRATION add constraint REGISTRATION_PATIENT_FK1 foreign key (PATIENT_ID)
    references TEST_ACCOUNT.PATIENT (PATIENT_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.REGISTRATION add constraint REGISTRATION_PRESCRIPTION_FK1 foreign key (PRESCRIPTIONID)
    references TEST_ACCOUNT.PRESCRIPTION (PRESCRIPTION_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.TREATMENT_FEEDBACK add constraint TREATMENT_FEEDBACK_TREATM_FK1 foreign key (DIAGNOSEDID)
    references TEST_ACCOUNT.TREATMENT_RECORD (DIAGNOSIS_RECORD_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.TREATMENT_RECORD add constraint TREATMENT_RECORD_DOCTOR_FK1 foreign key (DOCTOR_ID)
    references TEST_ACCOUNT.DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.TREATMENT_RECORD add constraint TREATMENT_RECORD_LEAVE_AP_FK1 foreign key (LEAVE_NOTE_ID)
    references TEST_ACCOUNT.LEAVE_APPLICATION (LEAVE_NOTE_ID) on delete cascade on update restrict;

alter table TEST_ACCOUNT.TREATMENT_RECORD add constraint TREATMENT_RECORD_PATIENT_FK1 foreign key (PATIENT_ID)
    references TEST_ACCOUNT.PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table TEST_ACCOUNT.TREATMENT_RECORD2 add constraint TREATMENT_RECORD2_TREATME_FK1 foreign key (DIAGNOSE_ID)
    references TEST_ACCOUNT.TREATMENT_RECORD (DIAGNOSIS_RECORD_ID) on delete cascade on update restrict;

