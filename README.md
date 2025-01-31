# 🔧 데이터베이스 프로젝트

<br>

## 🔬 프로젝트 목표
- 데이터베이스 성능 향상
- 효율적인 데이터 관리

<br>

## ⏳ 프로젝트 기간
1개월 (2025년 1월 - 2025년 2월)

<br>


---

## 🔖 기획서
데이터베이스에서 발생할 수 있는 문제를 파악하고 해결 방안을 제시
<br><br>
### 1️⃣ 성능 문제

#### 🔍 문제 상황
- 복잡한 쿼리로 인해 느린 실행 속도
- 테이블 크기 증가로 인한 성능 저하
- 비효율적인 인덱싱으로 인한 쿼리 성능 저하
  <br><br>


#### 💡 해결 방안
- **쿼리 최적화**: 실행 계획 분석 및 개선
- **인덱스 최적화**:
  - 자주 사용되는 컬럼에 인덱스 추가
  - 과도한 인덱스 생성을 방지
- **대용량 데이터 처리**:
  - Spring Batch를 사용하여 특정 시간마다 100만 건의 데이터를 삽입
- **캐싱**:
  - Redis를 사용하여 조회 요청을 줄임
    <br><br>


#### ✅ 테스트 결과
#### 1️⃣ 100만개의 테스트 엔티티 삽입 시도
- 코드로 배치 구현시 (스프링배치X) : OutOfMemory 발생. 시간초과로 모두 롤백되어 삽입 안됨
- 스프링 배치 사용시 : 1m 32s 성공
<br>

#### 2️⃣ 인덱스 여부에 따른 조회 성능 비교
총 데이터 500만
- 자주 조회될 것으로 예상되는 컬럼에 단일인덱스를 추가
- 인덱스 없을 때 : 1s
- 인덱스 설정 후 : 0.004s
<br>

#### 3️⃣ 캐싱 여부에 따른 조회 성능 비교
총 데이터 500만
- 레디스 사용 X : 43ms
- 레디스 사용 O : 2ms
  <br><br>
---

### 2️⃣ 동시성 문제

#### 🔍 문제 상황
- **데드락(Deadlock)**: 트랜잭션이 서로를 대기하며 중단되는 현상
- **락 경합(Lock Contention)**: 여러 트랜잭션이 동일한 자원을 동시에 요청
- **데이터 정합성 문제**: 동시 업데이트로 인해 데이터 불일치 발생
  <br><br>


#### 💡 해결 방안
- **트랜잭션 격리 수준 조정**:
  - READ COMMITTED 또는 REPEATABLE READ로 설정
- **락 타임아웃 설정**:
  - 트랜잭션 대기 시간을 제한하여 데드락 방지
- **낙관적 락과 비관적 락 사용**:
  - 경합 가능성에 따라 적절히 선택
- **큐 기반 처리**:
  - 중요한 작업은 메시지 큐를 통해 순차적으로 처리하여 데이터 일관성을 보장
  - RabbitMQ
  - [Kafka](study/kafka.md)
    <br><br>
---


### 3️⃣ 확장성 문제 

#### 🔍 문제 상황
- **읽기 성능 문제**: 데이터 요청량 증가로 인해 DB가 병목.
- **쓰기 성능 문제**: 많은 트랜잭션으로 인해 쓰기 병목 발생.
  <br><br>

#### 💡 해결 방안
- **읽기 확장**:
  - 리드 레플리카(Read Replica) 설정
- **읽기 확장**:
  - 샤딩(Sharding)을 통해 테이블을 분리
    <br><br>

---




