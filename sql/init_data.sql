use hospital;

# 生成固定资产（病房和病床）

# 创建4间病房
insert into room values('r01', '轻症区域');
insert into room values('r02', '轻症区域');
insert into room values('r03', '重症区域');
insert into room values('r04', '危重症区域');

# 为每个病房创建病床
insert into bed values('b0101');
insert into bed values('b0102');
insert into bed values('b0103');
insert into bed values('b0104');

insert into room_in_bed values('r01', 'b0101');
insert into room_in_bed values('r01', 'b0102');
insert into room_in_bed values('r01', 'b0103');
insert into room_in_bed values('r01', 'b0104');

insert into bed values('b0201');
insert into bed values('b0202');
insert into bed values('b0203');
insert into bed values('b0204');

insert into room_in_bed values('r02', 'b0201');
insert into room_in_bed values('r02', 'b0202');
insert into room_in_bed values('r02', 'b0203');
insert into room_in_bed values('r02', 'b0204');

insert into bed values('b0301');
insert into bed values('b0302');

insert into room_in_bed values('r03', 'b0301');
insert into room_in_bed values('r03', 'b0302');

insert into bed values('b0401');

insert into room_in_bed values('r04', 'b0401');

# 生成用户

# 创建3个主治医生
insert into user values('ud01', 'doctor1', '111', '张一', '主治医生', '轻症区域');
insert into user values('ud02', 'doctor2', '111', '王二', '主治医生', '重症区域');
insert into user values('ud03', 'doctor3', '111', '李三', '主治医生', '危重症区域');

# 创建3个护士长
insert into user values('uc01', 'chief1', '111', '赵一', '护士长', '轻症区域');
insert into user values('uc02', 'chief2', '111', '吴二', '护士长', '重症区域');
insert into user values('uc03', 'chief3', '111', '徐三', '护士长', '危重症区域');

# 创建1个急诊护士
insert into user values('ue01', 'emergency1', '111', '华一', '急诊护士', null);

# 创建9个病房护士
insert into user values('uw01', 'ward1', '111', '许一', '病房护士', '轻症区域');
insert into user values('uw02', 'ward2', '111', '付二', '病房护士', '轻症区域');
insert into user values('uw03', 'ward3', '111', '梅三', '病房护士', '轻症区域');
insert into user values('uw04', 'ward4', '111', '曹四', '病房护士', '轻症区域');
insert into user values('uw05', 'ward5', '111', '冯五', '病房护士', '轻症区域');
insert into user values('uw06', 'ward6', '111', '韩六', '病房护士', '轻症区域');
insert into user values('uw07', 'ward7', '111', '朱七', '病房护士', '重症区域');
insert into user values('uw08', 'ward8', '111', '熊八', '病房护士', '重症区域');
insert into user values('uw09', 'ward9', '111', '余九', '病房护士', '危重症区域');

# 生成患者

# 创建3个轻症患者
insert into patient values('pm01', '刘一', '310', '南京路', '在院治疗', '轻症');
insert into patient values('pm02', '武二', '310', '南京路', '在院治疗', '轻症');
insert into patient values('pm03', '郑三', '310', '南京路', '在院治疗', '轻症');

# 创建1个重症患者
insert into patient values('ps01', '魏一', '310', '南京路', '在院治疗', '重症');

# 分配病床和护士
insert into bed_for_patient values('b0101', 'pm01');
insert into nurse_for_patient values('uw01', 'pm01');

insert into bed_for_patient values('b0102', 'pm02');
insert into nurse_for_patient values('uw02', 'pm02');

insert into bed_for_patient values('b0301', 'ps01');
insert into nurse_for_patient values('uw07', 'ps01');

# 创建每天的信息登记
insert into daily_state values('dm0101', 'pm01', date'2020-11-11', 37.4, '咳嗽', '阴性', '在院治疗');
insert into daily_state values('dm0102', 'pm01', date'2020-11-12', 37.1, '咳嗽', '阴性', '在院治疗');
insert into daily_state values('dm0103', 'pm01', date'2020-11-13', 37.0, '无症状', '阴性', '在院治疗');
insert into daily_state values('dm0104', 'pm01', date'2020-11-14', 37.1, '无症状', '阴性', '在院治疗');

insert into daily_state values('dm0201', 'pm02', date'2020-11-12', 37.1, '咳嗽', '阴性', '在院治疗');
insert into daily_state values('dm0202', 'pm02', date'2020-11-13', 37.4, '无症状', '阴性', '在院治疗');
insert into daily_state values('dm0203', 'pm02', date'2020-11-14', 37.2, '无症状', '阴性', '在院治疗');

insert into daily_state values('dm0301', 'pm03', date'2020-11-14', 37.1, '无症状', '阴性', '在院治疗');

insert into daily_state values('ds0101', 'ps01', date'2020-11-12', 37.9, '肺炎', '阳性', '在院治疗');
insert into daily_state values('ds0102', 'ps01', date'2020-11-13', 37.8, '肺炎', '阳性', '在院治疗');
insert into daily_state values('ds0103', 'ps01', date'2020-11-14', 37.9, '肺炎', '阳性', '在院治疗');

# 创建核酸检测单
insert into test_sheet values('tm0101', 'pm01', date'2020-11-12', '阴性', '轻症');
insert into test_sheet values('tm0102', 'pm01', date'2020-11-14', '阴性', '轻症');

insert into test_sheet values('tm0201', 'pm02', date'2020-11-12', '阴性', '轻症');
insert into test_sheet values('tm0202', 'pm02', date'2020-11-14', '阴性', '轻症');

insert into test_sheet values('tm0301', 'pm03', date'2020-11-14', '阴性', '轻症');

insert into test_sheet values('ts0301', 'ps01', date'2020-11-12', '阳性', '重症');
insert into test_sheet values('ts0302', 'ps01', date'2020-11-13', '阳性', '重症');
insert into test_sheet values('ts0303', 'ps01', date'2020-11-14', '阳性', '重症');