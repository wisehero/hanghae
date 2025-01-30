package chap07.autodebit;

public class JpaAutoDebitInfoRepository implements AutoDebitInfoRepository {
	@Override
	public void save(AutoDebitInfo info) {
		// JPA를 사용해서 DB에 저장하는 코드
	}

	@Override
	public AutoDebitInfo findOne(String userId) {
		// JPA를 사용해서 userId로 DB에서 AutoDebitInfo를 조회하는 코드
		return null;
	}
}
