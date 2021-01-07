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
insert into user values('uw01', 'ward1', '111', '许一', '病房护士', null);
insert into user values('uw02', 'ward2', '111', '付二', '病房护士', null);
insert into user values('uw03', 'ward1', '111', '梅三', '病房护士', null);
insert into user values('uw04', 'ward1', '111', '曹四', '病房护士', null);
insert into user values('uw05', 'ward1', '111', '冯五', '病房护士', null);
insert into user values('uw06', 'ward1', '111', '韩六', '病房护士', null);
insert into user values('uw07', 'ward1', '111', '朱七', '病房护士', null);
insert into user values('uw08', 'ward1', '111', '熊八', '病房护士', null);
insert into user values('uw09', 'ward1', '111', '余九', '病房护士', null);