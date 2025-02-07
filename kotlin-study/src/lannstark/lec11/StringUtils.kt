package lannstark.lec11

// static 메서드는 아래와 같이 파일에다가 직접 정의하는게 좋다.
fun isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}