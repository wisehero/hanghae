## Week3


### 메소드 설계

```kotlin
fun 메소드이름(변수명:자료형, 변수명:자료형 ....) : 반환자료형 {
		소스코드 로직
}
```

- 메소드는 입력값을 받아서 처리한 후 결과값을 반환한다.
- 로직을 추상화해놓고 상황에 맞게 실행할 수 있다.
- 코드의 재사용성을 높일 수 있다.

### 클래스

- data class : 프로퍼티만 담고 있는 클래스 
- object class : 자바의 static을 대신하는 클래스
- open class : 상속이 가능한 클래스
- sealed class : 무분별한 상속을 방지하는 클래스

### 객체

- 인스턴스를 포함하는 개념
- 클래스 타입으로 선언된 것들을 객체라고 한다.
- 인스턴스는 클래스 형태로 설계된 객체를 실체화한 것이다.
- 인스턴스는 메모리 공간을 차지한다.