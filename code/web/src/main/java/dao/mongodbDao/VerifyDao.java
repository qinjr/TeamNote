package dao.mongodbDao;

import model.mongodb.Verify;

import java.util.List;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface VerifyDao {
    int addVerify(Verify verify);
    void deleteVerify(Verify verify);
    void updateVerify(Verify verify);
    Verify getVerifyById(int verifyId);
    List<Verify> getAllVerifies();
    List<Verify> getUncheckedVerifies();
}
