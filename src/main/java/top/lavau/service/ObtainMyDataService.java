package top.lavau.service;

import top.lavau.entity.MixedData;

import java.util.List;

public interface ObtainMyDataService {

    List<MixedData> obtainMyDataByTypeId(Integer typeId);
}
