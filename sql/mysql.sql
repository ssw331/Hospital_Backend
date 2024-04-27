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
    ADMINISTRATOR_ID     varchar(20) not null comment '?????ID',
    NAME                 varchar(20) comment '????',
    GENDER               numeric(1,0) comment '???',
    BIRTHDATE            datetime comment '??????????????????????????????',
    CONTACT              varchar(20) comment '??????',
    PASSWORD             varchar(200) comment '????',
    primary key (ADMINISTRATOR_ID)
);

alter table TEST_ACCOUNT.ADMINISTRATOR comment '?????';

/*==============================================================*/
/* Table: CHATRECORD                                            */
/*==============================================================*/
create table TEST_ACCOUNT.CHATRECORD
(
    RECORDID             varchar(30) comment '��????????????',
    DOCTOR_ID            varchar(20) not null comment '???��???????????',
    PATIENT_ID           varchar(20) not null comment '????��???????????',
    MESSAGE              varchar(2000) comment '???????????????????????��??',
    SENDER_TYPE          numeric(8,0) comment '???????????????????',
    TIMESTAMP            timestamp not null comment '??????????',
    READ_STATUS          numeric(8,0) comment '?????????',
    primary key (DOCTOR_ID, PATIENT_ID, TIMESTAMP)
);

/*==============================================================*/
/* Table: CONSULTATION_INFO                                     */
/*==============================================================*/
create table TEST_ACCOUNT.CONSULTATION_INFO
(
    DOCTOR_ID            varchar(10) not null comment '???ID',
    CLINIC_NAME          varchar(200) not null comment '????????',
    DATE_TIME            datetime not null comment '????????',
    "PERIOD"               numeric(8,0) not null comment '???????��??????',
    primary key (DOCTOR_ID, CLINIC_NAME, DATE_TIME, PERIOD)
);

alter table TEST_ACCOUNT.CONSULTATION_INFO comment '???????';

/*==============================================================*/
/* Table: CONSULTING_ROOM                                       */
/*==============================================================*/
create table TEST_ACCOUNT.CONSULTING_ROOM
(
    CONSULTING_ROOM_NAME varchar(30) not null comment '????????',
    CONSULTANT_CAPACITY  numeric(8,0) comment '????????',
    primary key (CONSULTING_ROOM_NAME)
);

alter table TEST_ACCOUNT.CONSULTING_ROOM comment '????';

/*==============================================================*/
/* Table: DEPARTMENT2                                           */
/*==============================================================*/
create table TEST_ACCOUNT.DEPARTMENT2
(
    DEPARTMENT_NAME      varchar(80) not null comment '????????',
    DEPARTMENT_DESCRIPTION varchar(800) comment '????????',
    primary key (DEPARTMENT_NAME)
);

alter table TEST_ACCOUNT.DEPARTMENT2 comment '????????';

/*==============================================================*/
/* Table: DOCTOR                                                */
/*==============================================================*/
create table TEST_ACCOUNT.DOCTOR
(
    DOCTOR_ID            varchar(20) not null comment '???ID',
    NAME                 varchar(20) comment '????',
    GENDER               numeric(1,0) comment '???',
    BIRTHDATE            datetime comment '??????????????????????????',
    TITLE                varchar(20) comment '???',
    CONTACT              varchar(20) comment '??????',
    SECONDARY_DEPARTMENT varchar(50) comment '????????????',
    PHOTOURL             varchar(600) comment '??????url',
    PASSWORD             varchar(200) comment '????',
    SKILLEDIN            varchar(1000) comment '?��????????????',
    primary key (DOCTOR_ID)
);

alter table TEST_ACCOUNT.DOCTOR comment '???';

/*==============================================================*/
/* Table: LEAVE_APPLICATION                                     */
/*==============================================================*/
create table TEST_ACCOUNT.LEAVE_APPLICATION
(
    LEAVE_NOTE_ID        varchar(30) not null comment '????ID',
    LEAVE_APPLICATION_TIME timestamp comment '???????',
    LEAVE_START_DATE     timestamp comment '???????',
    LEAVE_END_DATE       timestamp comment '????????',
    LEAVE_NOTE_REMARK    varchar(10) comment '????????',
    primary key (LEAVE_NOTE_ID)
);

alter table TEST_ACCOUNT.LEAVE_APPLICATION comment '????????';

/*==============================================================*/
/* Table: MEDICINE_DESCRIPTION                                  */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_DESCRIPTION
(
    MEDICINE_NAME        varchar(100) not null comment '??????',
    MEDICINE_TYPE        varchar(40) comment '??????',
    APPLICABLE_SYMPTOM   varchar(600) comment '???��??',
    VULGO                varchar(200) comment '?????????????????',
    SPECIFICATION        varchar(500) comment '?????',
    SINGLEDOSE           varchar(300) comment '???��???',
    ADMINISTRATION       varchar(400) comment '???��???',
    ATTENTION            varchar(400) comment '???????',
    FREQUENCY            varchar(200) comment '???????',
    primary key (MEDICINE_NAME)
);

alter table TEST_ACCOUNT.MEDICINE_DESCRIPTION comment '?????';

/*==============================================================*/
/* Table: MEDICINE_OUT                                          */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_OUT
(
    MEDICINE_NAME        varchar(40) not null comment '??????',
    MANUFACTURER         varchar(40) not null comment '????????',
    PRODUCTION_DATE      datetime not null comment '????????',
    PURCHASE_AMOUNT      numeric(8,0) not null comment '????????',
    DELIVER_DATE         timestamp not null comment '????????',
    PATIENT_ID           varchar(20) not null comment '??????',
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE, PURCHASE_AMOUNT, DELIVER_DATE, PATIENT_ID),
    check (PURCHASE_AMOUNT > 0)
);

alter table TEST_ACCOUNT.MEDICINE_OUT comment '??????';

/*==============================================================*/
/* Table: MEDICINE_PURCHASE                                     */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_PURCHASE
(
    MEDICINE_NAME        varchar(40) not null comment '??????',
    MANUFACTURER         varchar(460) not null comment '????????',
    PRODUCTION_DATE      datetime not null comment '????????',
    PURCHASE_DATE        datetime not null comment '???????',
    ADMINISTRATOR_ID     varchar(20) comment '????????',
    PURCHASE_AMOUNT      numeric(8,0) comment '???????',
    PURCHASE_PRICE       numeric(6,2) comment '??????',
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE, PURCHASE_DATE),
    check (PURCHASE_PRICE > 0)
);

alter table TEST_ACCOUNT.MEDICINE_PURCHASE comment '??????';

/*==============================================================*/
/* Table: MEDICINE_SELL                                         */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_SELL
(
    MEDICINE_NAME        varchar(40) not null comment '??????',
    MANUFACTURER         varchar(80) not null comment '????????',
    SELLING_PRICE        numeric(6,2) comment '?????',
    primary key (MEDICINE_NAME, MANUFACTURER)
);

alter table TEST_ACCOUNT.MEDICINE_SELL comment '??????';

/*==============================================================*/
/* Table: MEDICINE_STOCK                                        */
/*==============================================================*/
create table TEST_ACCOUNT.MEDICINE_STOCK
(
    MEDICINE_NAME        varchar(40) not null comment '??????',
    MANUFACTURER         varchar(40) not null comment '????????',
    PRODUCTION_DATE      datetime not null comment '????????',
    MEDICINE_SHELFLIFE   numeric(8,0) comment '??????',
    MEDICINE_AMOUNT      numeric(8,0) comment '??????',
    THRESHOLD_VALUE      numeric(8,0) comment '?????????',
    CLEAN_DATE           datetime comment '??????????',
    CLEAN_ADMINISTRATOR  varchar(20) comment '??????????',
    primary key (MEDICINE_NAME, MANUFACTURER, PRODUCTION_DATE)
);

alter table TEST_ACCOUNT.MEDICINE_STOCK comment '?????';

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

alter table TEST_ACCOUNT.OUTPATIENT_ORDER comment '??????';

/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
create table TEST_ACCOUNT.PATIENT
(
    PATIENT_ID           varchar(20) not null comment '??????ID',
    NAME                 varchar(20) comment '??????????',
    GENDER               numeric(1,0) comment '???',
    BIRTH_DATE           datetime comment '??????????????????',
    CONTACT              varchar(20) comment '??????',
    PASSWORD             varchar(200) comment '????',
    COLLEGE              varchar(40) comment '??',
    COUNSELLOR           varchar(20) comment '?????',
    primary key (PATIENT_ID)
);

alter table TEST_ACCOUNT.PATIENT comment '??????';

/*==============================================================*/
/* Table: PRESCRIPTION                                          */
/*==============================================================*/
create table TEST_ACCOUNT.PRESCRIPTION
(
    PRESCRIPTION_ID      varchar(40) not null comment '???????',
    TOTAL_PRICE          numeric(6,2) comment '????',
    DOCTOR_ID            varchar(20) comment '??????',
    PAYSTATE             numeric(8,0) comment '0?��?????1????',
    primary key (PRESCRIPTION_ID)
);

alter table TEST_ACCOUNT.PRESCRIPTION comment '????';

/*==============================================================*/
/* Table: PRESCRIPTION_MEDICINE                                 */
/*==============================================================*/
create table TEST_ACCOUNT.PRESCRIPTION_MEDICINE
(
    PRESCRIPTION_ID      varchar(40) not null comment '???????',
    MEDICINE_NAME        varchar(200) not null comment '??????',
    MEDICATION_INSTRUCTION varchar(800) comment '???��??',
    MEDICINE_PRICE       numeric(6,2) comment '?????',
    QUANTITY             numeric(8,0) comment '??????',
    primary key (PRESCRIPTION_ID, MEDICINE_NAME)
);

alter table TEST_ACCOUNT.PRESCRIPTION_MEDICINE comment '????????';

/*==============================================================*/
/* Table: REGISTRATION                                          */
/*==============================================================*/
create table TEST_ACCOUNT.REGISTRATION
(
    PATIENT_ID           varchar(20) not null comment '??????ID',
    DOCTOR_ID            varchar(20) not null comment '???ID',
    APPOINTMENT_TIME     datetime not null comment '?????',
    "PERIOD"               numeric(8,0) not null comment '?????��????8??00-9??00?1??9??00-10??00?2????????????????16??00-17??00?7??11???��?13?????',
    REGISTORDER          numeric(8,0) comment '??????',
    STATE                numeric(8,0) not null comment '??????????��????0???????1????????-1',
    PRESCRIPTIONID       varchar(200) comment '??????????',
    CHECKIN              numeric(8,0) comment '??????????0??��?1????',
    QRCODEURL            varchar(2000) comment '?????????url',
    ORDERTIME            timestamp comment '??????',
    primary key (PATIENT_ID, DOCTOR_ID, APPOINTMENT_TIME, STATE, PERIOD)
);

alter table TEST_ACCOUNT.REGISTRATION comment '???';

/*==============================================================*/
/* Table: TEMPLATE                                              */
/*==============================================================*/
create table TEST_ACCOUNT.TEMPLATE
(
    PROBLEM              varchar(100) comment '????',
    ILLNESS              varchar(50) comment '????',
    COLUMN1              varchar(50) comment '?????',
    SYMPTOM              varchar(50) comment '???',
    DIAGNOSE             varchar(30) comment '???',
    PRESCRIPTION         varchar(20) comment '???????',
    MEDICINE             varchar(70) comment '????',
    NAME                 varchar(40) not null comment '????',
    primary key (NAME)
);

/*==============================================================*/
/* Table: TREATMENT_FEEDBACK                                    */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_FEEDBACK
(
    PATIENT_ID           varchar(20) comment '??????ID v',
    DOCTOR_ID            varchar(20) comment '???ID',
    TREATMENT_SCORE      numeric(8,0) comment '????????',
    EVALUATION           varchar(200) comment '???????',
    DIAGNOSEDID          varchar(40) not null comment '???????',
    primary key (DIAGNOSEDID)
);

alter table TEST_ACCOUNT.TREATMENT_FEEDBACK comment '??????';

/*==============================================================*/
/* Table: TREATMENT_RECORD                                      */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_RECORD
(
    DIAGNOSIS_RECORD_ID  varchar(30) not null comment '?????ID',
    DOCTOR_ID            varchar(20) comment '???ID',
    PATIENT_ID           varchar(20) comment '??????ID',
    LEAVE_NOTE_ID        varchar(30) comment '????ID',
    primary key (DIAGNOSIS_RECORD_ID)
);

alter table TEST_ACCOUNT.TREATMENT_RECORD comment '?????';

/*==============================================================*/
/* Table: TREATMENT_RECORD2                                     */
/*==============================================================*/
create table TEST_ACCOUNT.TREATMENT_RECORD2
(
    DIAGNOSE_ID          varchar(30) not null comment '????????',
    DIAGNOSE_TIME        timestamp comment '??????',
    COMMENTSTATE         numeric(8,0) comment '???????????????0��?????1????',
    SELFREPORTED         varchar(2000) comment '????',
    PRESENTHIS           varchar(2000) comment '????',
    ANAMNESIS            varchar(2000) comment '???????',
    SIGN                 varchar(1000) comment '????',
    CLINICDIA            varchar(2000) comment '???????',
    ADVICE               varchar(2000) comment '???????',
    KINDQUANTITY         numeric(8,0) comment '??????????',
    primary key (DIAGNOSE_ID)
);

alter table TEST_ACCOUNT.TREATMENT_RECORD2 comment '?????';

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

