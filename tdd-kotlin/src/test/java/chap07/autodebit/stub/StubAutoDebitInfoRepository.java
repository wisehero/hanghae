package chap07.autodebit.stub;

import chap07.autodebit.AutoDebitInfo;
import chap07.autodebit.AutoDebitInfoRepository;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {

	@Override
	public void save(AutoDebitInfo info) {
		// do nothing
	}

	@Override
	public AutoDebitInfo findOne(String userId) {
		return null;
	}
}
