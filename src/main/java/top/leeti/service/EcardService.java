package top.leeti.service;

import top.leeti.entity.Ecard;

public interface EcardService {

    boolean insertEcard(Ecard ecard);

    Ecard getEcardById(String id);

    void claim(String id);

    boolean updateEcard(Ecard ecard);
}
