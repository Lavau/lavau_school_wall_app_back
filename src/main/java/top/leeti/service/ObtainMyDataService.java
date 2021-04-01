package top.leeti.service;

import top.leeti.entity.MixedData;

import java.util.List;

public interface ObtainMyDataService {

    List<MixedData> obtainMyDataByTypeId(Integer typeId);
}
