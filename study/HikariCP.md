# 📌HikariCP


- **스프링 부트에서 기본적으로 제공하는 커넥션 풀**
<br><br>


## 📌 커넥션 풀 
- 데이터베이스 연결(Connection)을 미리 생성해서 재사용하는 기술
- 데이터베이스에 접근할 때마다 새롭게 연결을 생성하는 것이 아니라, 미리 만들어진 커넥션을 재사용하여 성능을 최적화
  <br><br>
 
### ❓ 왜 커넥션 풀이 필요할까?
- 데이터베이스에 접속하는 과정은 시간이 오래 걸리는 작업으로 일반적으로 다음과 같다.
  <br><br>
#### ⛔ 커넥션 풀이 없을 경우 (비효율적인 방식)
    1️⃣ 클라이언트가 데이터베이스 연결 요청
    2️⃣ 새로운 커넥션을 생성 (DB 연결)
    3️⃣ SQL 실행
    4️⃣ 결과 반환 후 커넥션 종료 (연결 해제)
    5️⃣ 다시 요청이 오면 위 과정 반복

- 👉 이 방식은 매 요청마다 DB 커넥션을 새로 생성하고 종료하기 때문에, DB 부하가 증가하고 성능이 저하 🚨
  <br><br>

#### ✅ 커넥션 풀이 있는 경우 (효율적인 방식)
    1️⃣ 서버 시작 시 미리 일정 개수의 DB 커넥션을 생성하여 풀(Pool)에 저장
    2️⃣ 클라이언트가 요청하면 풀에서 사용 가능한 커넥션을 가져옴
    3️⃣ SQL 실행 후 커넥션을 닫지 않고 풀에 반환 (재사용 가능)
    4️⃣ 요청이 많아지면 풀 크기만큼 커넥션 추가 생성
    5️⃣ 일정 시간이 지나거나 사용하지 않으면 커넥션을 정리 (Idle Timeout)
- 👉 이렇게 하면 DB 커넥션을 재사용하여 성능이 크게 향상됨! 🚀
  <br><br>



## 📌 커넥션 풀 관리 방법 
### 1️⃣ maximum-pool-size 
- 동시에 사용할 수 있는 최대 커넥션 개수
- 너무 크면 불필요한 리소스 사용이 증가하고, 너무 작으면 대기 시간이 길어짐.
  <br><br>
### 2️⃣ minimum-idle
- 풀에서 유지하는 최소 유휴(아무것도 하지 않고 대기중인) 커넥션 개수
- minimum-idle 값이 너무 크면 불필요한 커넥션 유지 비용 증가,
- 너무 작으면 요청이 올 때마다 새로 커넥션을 생성해야 해서 응답 속도 저하 발생.
  <br><br>
### 3️⃣ idle-timeout 설정 (유휴 상태 커넥션 정리)
- 사용되지 않는 커넥션을 일정 시간 후 제거하여 불필요한 리소스 낭비 방지.
  <br><br>
### 4️⃣ max-lifetime 설정
- 커넥션의 최대 수명
- DB 커넥션이 너무 오래 유지되면 DB와의 연결이 끊기거나 비정상 상태가 될 수 있음.
- 일정 시간이 지나면 강제로 커넥션을 새로 생성하는 것이 좋음.
  <br><br>
### 5️⃣ connection-timeout 설정 
- 커넥션을 얻을때 대기하는 최대 시간 
- 사용 가능한 커넥션이 없을 때, 최대 몇 초까지 기다릴지 설정.
- 값이 너무 크면 요청이 오래 걸릴 수 있고, 너무 작으면 에러 발생 가능.
  <br><br>
### 6️⃣ 필요 없는 쿼리는 커넥션 반환 후 실행
- 쿼리를 실행한 후에도 불필요한 작업을 오래 수행하면 커넥션이 반환되지 않음 → 성능 저하 발생!
- 가능하면 쿼리 실행 후 바로 커넥션을 반환한 다음 추가 작업 수행.
  <br><br>



##  📌 JPA에서의 커넥션 풀

### 1️⃣ 트랜잭션 시작
- @Transactional이 선언된 메서드 호출
- Spring의 TransactionInterceptor가 동작
- EntityManager가 활성화되면서 HikariCP에서 커넥션을 가져옴

### 2️⃣ JPA가 쿼리를 실행
- entityManager.persist(), entityManager.find(), repository.save(), repository.findById() 등의 메서드 호출
- Hibernate가 SQL을 생성하고 실행

### 3️⃣ 트랜잭션 종료 (commit or rollback)
- @Transactional이 끝나면 Spring이 트랜잭션을 종료
- 커밋되면 Hibernate가 flush()를 호출하여 SQL을 실행
- 커넥션을 풀에 반환 (물리적인 연결이 닫히지 않고 재사용됨)

💡 JPA에서는 트랜잭션 단위로 커넥션을 할당하고, 트랜잭션이 끝나면 자동으로 커넥션을 반환
<br><br>

### 🔥 @Transactional 없이 JPA를 사용할 경우
- @Transactional이 없으면 쿼리를 실행할 때마다 새로운 커넥션을 요청하고 즉시 반환함.
- 하지만 @Transactional을 사용하면 트랜잭션이 끝날 때까지 하나의 커넥션을 유지하고 한꺼번에 커밋.

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser() {
        User user = new User();
        user.setName("John");
        userRepository.save(user); // ⬅ 쿼리 실행할 때마다 커넥션을 요청하고 즉시 반환
    }
}

```
<br><br>
### 🚨 커넥션 반환이 제대로 이루어지지 않는 경우
- JPA에서는 보통 트랜잭션이 끝날 때 커넥션을 반환하지만, 커넥션 반환이 지연되는 경우가 있음.
  <br><br>
#### 1️⃣  영속성 컨텍스트를 flush() 하지 않고 유지하는 경우
- @Transactional(readOnly = true) 설정 시
- 영속성 컨텍스트는 유지되지만 flush()가 호출되지 않아 커넥션이 반환되지 않을 수 있음.
- 읽기 전용 쿼리는 네이티브 쿼리로 실행하거나, 적절히 flush() 호출 필요.
```java
@Transactional(readOnly = true)
public void getUsers() {
    List<User> users = userRepository.findAll(); // SELECT 실행
    // 커넥션이 반환되지 않을 가능성 있음 (영속성 컨텍스트 유지)
}

```
<br><br>
#### 2️⃣ EntityManager를 직접 사용하면서 close()를 호출하지 않는 경우
- ntityManager를 직접 사용하는 경우 close()를 명시적으로 호출해야 커넥션이 반환됨.

```java
public void manualEntityManager() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    
    User user = new User();
    user.setName("Alice");
    em.persist(user);
    
    em.getTransaction().commit();
    em.close(); // ⬅ 커넥션 반환
}

```
