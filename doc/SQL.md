# 核心SQL语句说明

### 一、需求分析
根据PJ文档，我们归纳总结了以下需求：
1. 查询：
	1. 主治医生：
		1. 查看当前治疗区域的病人信息
		2. 查看当前治疗区域的护士长及病房护士信息
		3. 查看病房护士负责的病人
	2. 护士长：
		1. 查看当前治疗区域的病人信息
		2. 查看当前治疗区域的病房护士信息
		3. 查看病房护士负责的病人
		4. 查看当前治疗区域的病床信息
		5. 查看病床的病人
	3. 急诊护士：
		1. 查看各区域病人信息
	4. 病房护士：
		1. 查看自己负责的病人信息
2. 增加：
	1. 主治医生：
		1. 增加新的当前治疗区域的病人的核酸检测单
	2. 护士长：
		1. 增加新的当前治疗区域的病房护士
	3. 急诊护士：
		1. 增加新的病人信息
	4. 病房护士：
		1. 增加新的每日状态
3. 修改：
	1. 主治医生：
		1. 修改当前治疗区域的病人的病情评级和生命状态（轻症区域可以允许出院）
	2. 护士长：
		1. 无
	3. 急诊护士：
		1. 允许待转移的病人转移治疗区域
	4. 病房护士：
		1. 无
4. 删除：
	1. 主治医生：
		1. 无
	2. 护士长：
		1. 删除当前治疗区域的病房护士
	3. 急诊护士：
		1. 无
	4. 病房护士：
		1. 无

据此分析，比较关键的SQL语句应当实现以下几个方面的功能：
1. 病人信息的增改查
2. 病房护士信息的增删查
3. 病人与病床和护士对应关系的增删
4. 每日状态和核酸检测单的增

### 二、病人信息相关SQL语句
#### 1. 增
增加病人信息时并不直接添加病床和护士对应关系，只修改基本信息即可

`insert into patient values (?,?,?,?,?,?)`
#### 2. 改
修改病人信息时需要制定ID，并且只能修改生命状态和病情评级

`update patient set life_state=?, ill_state=? where p_ID=?`

#### 3. 查
最为关键的语句，由于需要查询返回的信息很多，并且需要支持不同的筛选条件，因此使用了嵌套+组装的写法。

`select * from (select X from Y) as patient_info where Z;`

其中X为：

```
    patient.p_ID as p_ID, patient.name as name, patient.resident_ID as resident_ID, patient.address as address, 
    patient.life_state as life_state, patient.ill_state as ill_state, 
    user.u_ID as u_ID, user.name as u_name, bed.b_ID as b_ID, room.r_ID as r_ID, room.area as area, 
    (select max(temp.temperature) as temperature
    from (select * from daily_state where p_ID=patient.p_ID order by date desc limit 3) as temp) as temperature,
    (select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1) as result1,
    (select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1, 1) as result2
```
这是需要返回的attribute，包括了计算得出的三天内最高体温和两个最近的核酸检测结果

Y为：

```
    patient 
        left join (nurse_for_patient natural join user) 
        on patient.p_ID = nurse_for_patient.p_ID 
        left join (bed_for_patient natural join bed natural join room_in_bed natural join room) 
        on patient.p_ID = bed_for_patient.p_ID
```

通过外联结将表格信息汇总

Z为拼装形成的筛选条件，例如：

```
    p_ID is not null and 
    (ill_state='轻症' and temperature < 37.3 and result1='阴性' and result2='阴性') is not true
```
这个条件筛选出所有满足出院条件的患者

### 三、病房护士信息相关SQL语句
#### 1. 增
`insert into user values (?,?,?,?,?,?)`

#### 2. 删
`delete from user where username=?`

#### 3. 查
需要查询某一治疗区域的所有病房护士：

`select * from user where post='病房护士' and area=?`

### 四、对应关系相关SQL语句
#### 1. 增
分别增加护士与病人以及病床与病人的从属关系

`insert into nurse_for_patient values (?,?)`

`insert into bed_for_patient values (?,?)`

#### 2. 删
分别删除护士与病人以及病床与病人的从属关系

`delete from nurse_for_patient where p_ID=?`

`delete from bed_for_patient where p_ID=?`

### 五、每日状态和核酸检测单相关SQL语句
#### 1. 增
分别新增每日状态与核酸检测单条目
`insert into daily_state values (?,?,?,?,?,?,?)`

`insert into test_sheet values (?,?,?,?,?)`
