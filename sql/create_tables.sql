use hospital;

# user 用户，包含账户信息
create table user(
    u_ID varchar(256) NOT NULL,
    username varchar(256) NOT NULL, # 用户名
    password varchar(256) NOT NULL, # 密码
    name varchar(256) NOT NULL, # 用户姓名
    post enum('主治医生','护士长','病房护士','急诊护士') NOT NULL, # 医护人员类别
    area enum('轻症区域','重症区域','危重症区域'), # 所属治疗区域
    PRIMARY KEY(u_ID)
);

# room 病房
create table room(
    r_ID varchar(256) NOT NULL,
    area enum('轻症区域','重症区域','危重症区域') NOT NULL, # 所属治疗区域
    PRIMARY KEY (r_ID)
);

# bed 病床
create table bed(
    b_ID varchar(256) NOT NULL,
    PRIMARY KEY (b_ID)
);

# room_in_bed 病房与病床的对应关系
create table room_in_bed(
    r_ID varchar(256) NOT NULL,
    b_ID varchar(256) NOT NULL,
    PRIMARY KEY (r_ID, b_ID)
);

# patient 病人
create table patient(
    p_ID varchar(256) NOT NULL,
    name varchar(256) NOT NULL, #病人姓名
    resident_ID varchar(256) NOT NULL, #身份证号
    address varchar(256) NOT NULL, # 家庭住址
    life_state enum('康复出院','在院治疗','病亡') NOT NULL, # 生命状态
    ill_state enum('轻症','重症','危重症') NOT NULL, # 病情评级
    PRIMARY KEY(p_ID)
);

# bed_for_patient 病床与病人的对应关系
create table bed_for_patient(
    b_ID varchar(256) NOT NULL,
    p_ID varchar(256) NOT NULL,
    PRIMARY KEY (b_ID, p_ID)
);

# nurse_for_patient 护士与病人的对应关系
create table nurse_for_patient(
    u_ID varchar(256) NOT NULL,
    p_ID varchar(256) NOT NULL,
    PRIMARY KEY (u_ID, p_ID)
);

# daily_state 每天的信息登记
create table daily_state(
    d_ID varchar(256) NOT NULL,
    p_ID varchar(256) NOT NULL,
    date date NOT NULL, # 登记日期
    temperature decimal(3, 1) NOT NULL,
    symptom varchar(256) NOT NULL,
    result enum('阳性','阴性') NOT NULL, # 检测结果
    life_state enum('康复出院','在院治疗','病亡') NOT NULL, # 生命状态
    PRIMARY KEY(d_ID),
    CONSTRAINT person_date UNIQUE (p_ID, date)
);

# test_sheet 核酸检测单
create table test_sheet(
    t_ID varchar(256) NOT NULL,
    p_ID varchar(256) NOT NULL,
    date date NOT NULL, # 检测日期
    result enum('阳性','阴性') NOT NULL, # 检测结果
    ill_state enum('轻症','重症','危重症') NOT NULL, # 病情评级
    PRIMARY KEY(t_ID),
    CONSTRAINT person_date UNIQUE (p_ID, date)
);

create unique index patient_index on patient(p_ID);

create index daily_state_index on daily_state(d_ID,p_ID);

create index test_index on test_sheet(t_ID, p_ID);

create index nfp_index on nurse_for_patient(u_ID);

create index bfp_index on bed_for_patient(b_ID);



