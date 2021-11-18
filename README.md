# InPlane-Spring

> 평소 내가 계획적으로 일상을 사는지 측정해보고 공유할 수 있는 플랫폼으로, InPlan은 InBody에서 따온 말이다. 내가 일상을 어떻게 보내는지 측정을 해보는 시스템이다.
>

### Overview

우리는 일상을 특별할 기준없이 되는데로 일상을 보내는데 이러다보면 쓸데없이 낭비되는 시간이 많아진다. 왜냐하면 특별한 기준이 없기때문에 SNS를 보는데 시간을 너무 많이 보내거나 운동하는 시간이 부족해도 인지하지
못하기 때문이다. InPlane은 자신이 보낸 하루를 기록하고 공유함으로써 서로의 기준이 되어주고 부족한 부분을 채울 수 있다.

### 설계

> Language : JAVA
>
> WebFramework : Spring Boot
>
> Database : MYSQL

### Futures

> 일정과 사용자를 관리한다.

1. 일정 : 활동한 내용을 등록, 조회, 수정, 삭제를 한다.

    - 사용자

    - 일정 시작시간
    - 끝시간
    - 카테고리
    - 설명

2. 사용자 : 사용자 정보를 등록, 조회, 수정, 삭제를 한다.

    - 이름
    - 이메일

3. 카테고리 : 일정의 카테고리를 등록, 조회, 수정, 삭제를 한다.

    - 카테고리명

### Database

> 기능구현을 위해 데이터베이스를 설계한다.



![이미지](./doc/1.png)

#### user

| PK    | column | Type |
| ----- | ------ | ---- |
| :key: | id     | INT  |
|       | name   | TEXT |
|       | email  | TEXT |

#### plan

| PK    | column      | Type |
| ----- | ----------- | ---- |
| :key: | id          | INT  |
|       | user_id     | INT  |
|       | start_date  | DATE |
|       | end_date    | DATE |
|       | category_id | INT  |
|       | description | TEXT |

#### plan_category

| PK    | column | Type |
| ----- | ------ | ---- |
| :key: | id     | INT  |
|       | name   | TEXT |





