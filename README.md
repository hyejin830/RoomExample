# RoomExample

## Room을 이용한 데이터베이스 예제 해보기 

<img src="https://github.com/hyejin830/RoomExample/blob/master/images/1.png" width="30%"></img>| <img src="https://github.com/hyejin830/RoomExample/blob/master/images/2.png" width="30%"></img>| <img src="https://github.com/hyejin830/RoomExample/blob/master/images/3.png" width="30%"></img>

### 1. Room 이란 

Room은 SQLite의 추상 계층을 제공하여 SQLite의 모든 기능을 활용하면서 유창한 데이터베이스 액세스를 허용한다

이점 
`캐시`로 인해 오프라인 상태여도 내용을 탐색(리모트 데이터베이스를 사용할 때)

### 2. Dependencies 정의

```
def room_version = "1.1.1"

implementation "android.arch.persistence.room:runtime:$room_version"
annotationProcessor "android.arch.persistence.room:compiler:$room_version"
```

### 3. 3가지 구성요소

#### 3.1 Database

- 데이터베이스 홀더를 포함하고 앱에 지속적이고 안정적인 데이터를 연결을 위한 주요 액세스 포인트를 제공
- 조건 
    - @Database를 어노테이션
    - RoomDatabase를 상속받은 추상 클래스여야 한다
    - 어노테이션 안에서 데이터베이스와 관련된 개체의 목록을 포함해야 한다
    - 추상 메소드는 0개의 arguments를 가져야 하고, @Dao가 어노테이션된 클래스를 리턴해야 한다
- 실행 시, Room.databaseBuilder 또는 Romm.inMemoryDatabaseBuilder를 호출함으로써 Database의 인스턴스를 얻을 수 있다

#### 3.2 Entity
- database의 table을 나타낸다

#### 3.3 DAO
- database에 접근할 수 있는 메소드를 포함한다

### 4. 프로젝트

#### 4.0 아주 간단한 기능
- User 정보 Insert
- 모든 User 정보 Select

#### 4.1 User
- POJO이자 room Entity

#### 4.2 UserDao
- 쿼리문이 작성되어 있는 인터페이스

#### 4.3 AppDatabase
- UserDao 추상 클래스 선언
- 어디서나 쓰게 싱글톤 인스턴스 생성

#### 4.4 Activity
- RxJava 이용

### 참고
[1] https://developer.android.com/jetpack/androidx/releases/room
[2] https://developer.android.com/training/data-storage/room/index.html
[4] https://github.com/alahammad/RoomSample/blob/master/app/src/main/java/net/ahammad/roomsample/MainActivity.java


### 후에 참고하면 좋을 사이트

구글 샘플 - AAC
Room & RxJava Sample
https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample

Android Room with a View - Java
https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..index#0

Android Room with a View - Kotlin
https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#0
