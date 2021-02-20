package top.lavau.service;

import com.github.pagehelper.PageInfo;
import top.lavau.entity.Ecard;

public interface EcardService {

    boolean insertEcard(Ecard ecard);

    Ecard getEcardById(String id);

    void claim(String id);

    boolean updateEcard(Ecard ecard);
}
