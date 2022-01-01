
## REST API 구현 테스트 

### 💻 개발 환경 
+ JAVA 11 / Spring Boot / Tomcat
+ H2 / JPA
+ STS 4

<br/>

### 🎯 Entity 설정
+ Book : 도서 관련 테이블
+ Company : 계약 관련 테이블
+ Supply : 공급 관련 테이블
+ SupplyBook : 공급 도서 관련 테이블

<br/>

### 🔧 DB설정 
+ 로그인 설정 : Generic H2 (Embeded)
+ 드라이버 클래스 : org.h2.Driver
+ JDBC URL : jdbc:h2:tcp://localhost/~/test
+ 사용자 명 : sa
+ 패스워드 : 없음(공란)
+ 기타 : 최초 application 실행 후 데이터 저장을 위해 application.yml에서 ddl.auto를 update로 수정 필요

<br/>

### 🎈 세부 요건 정리
+  Supply 데이터 등록은 Company 엔티티가 등록될 때 자동으로 같이 삽입 (cascade 옵션)
+  계약업체 테이블의 경우 의미가 모호하여 계약업체명이라는 속성만 추가 후, 계약/계약업체 모두 관리 가능하도록 설정함
+  타입 지정하는 테이블은 열거형으로 관리 (Book - bookType / Company - contractStatusCode)
+  Entity 노출 최소화를 위한 공통DTO 사용

<br/>

### ⭐️ 구현 API
+ BookAPI
<img src="https://user-images.githubusercontent.com/78363202/147852637-bd6bafd5-f622-465b-9b15-0426d91c69b0.jpg?raw=true" width=50% height=50%/>

+ CompanyAPI
<img src="https://user-images.githubusercontent.com/78363202/147852639-fdedc4c6-ec8b-490a-8fb3-19758c88be44.JPG?raw=true" width=50% height=50%/>
  
+ SupplyBookAPI
<img src="https://user-images.githubusercontent.com/78363202/147852641-5b86a010-778d-467e-96de-8c860031944c.JPG?raw=true" width=50% height=50%/>


#### 테스트 및 자세한 API 설명은 Application 실행 후 http://localhost:8080/swagger-ui.html에서 확인 부탁드립니다!

<br/>

### 📞 Contact Info
+ jaeskaaa@gmail.com


　<br/>

